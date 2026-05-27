package com.example.workshop1.service;

import com.example.workshop1.dto.PostDto;
import com.example.workshop1.entity.Member;
import com.example.workshop1.entity.Post;
import com.example.workshop1.entity.PostImage;
import com.example.workshop1.exception.ResourceNotFoundException;
import com.example.workshop1.repository.PostImageRepository;
import com.example.workshop1.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository      postRepository;
    private final PostImageRepository imageRepository;
    private final MemberService       memberService;
    private final ImageService        imageService;

    public PostService(PostRepository postRepository,
                       PostImageRepository imageRepository,
                       MemberService memberService,
                       ImageService imageService) {
        this.postRepository  = postRepository;
        this.imageRepository = imageRepository;
        this.memberService   = memberService;
        this.imageService    = imageService;
    }

    /* ── 목록 ──────────────────────────────────── */
    public Page<PostDto.SummaryResponse> findAll(Pageable pageable) {
        return postRepository.findAllWithMember(pageable)
                             .map(PostDto.SummaryResponse::from);
    }

    /* ── 상세 ──────────────────────────────────── */
    public PostDto.Response findById(Long id) {
        return PostDto.Response.from(getPostEntity(id));
    }

    /* ── 등록 (작업형 1·3) ─────────────────────── */
    @Transactional
    public PostDto.Response create(String title, String content, Long memberId,
                                   List<MultipartFile> files) throws IOException {
        Member member = memberService.getMemberEntity(memberId);
        Post post     = postRepository.save(Post.create(title, content, member));

        if (files != null) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;
                ImageService.SavedImage saved = imageService.save(file);
                imageRepository.save(PostImage.create(
                    saved.originalName(), saved.storedName(),
                    saved.thumbnailName(), post));
            }
        }
        return PostDto.Response.from(post);
    }

    /* ── 수정 ──────────────────────────────────── */
    @Transactional
    public PostDto.Response update(Long id, String title, String content) {
        Post post = getPostEntity(id);
        post.update(title, content);
        return PostDto.Response.from(post);
    }

    /* ── 삭제 ──────────────────────────────────── */
    @Transactional
    public void delete(Long id) {
        Post post = getPostEntity(id);
        // 이미지 파일 삭제
        post.getImages().forEach(img ->
            imageService.delete(img.getStoredName(), img.getThumbnailName()));
        postRepository.delete(post);
    }

    /* ── 작업형 2: 본인 글 확인 ─────────────────── */
    public void validateAuthor(Long postId, Long memberId) {
        Post post = getPostEntity(postId);
        if (!post.getMember().getId().equals(memberId))
            throw new AccessDeniedException("본인 글만 수정·삭제할 수 있습니다.");
    }

    public Post getPostEntity(Long id) {
        return postRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("게시글을 찾을 수 없습니다. id=" + id));
    }
}
