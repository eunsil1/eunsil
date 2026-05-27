package com.example.board.service;

import com.example.board.dto.PostDto;
import com.example.board.entity.Member;
import com.example.board.entity.Post;
import com.example.board.exception.ResourceNotFoundException;
import com.example.board.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberService  memberService;

    public PostService(PostRepository postRepository, MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService  = memberService;
    }

    /* ── 목록 (페이징) ─────────────────────────────── */
    public Page<PostDto.SummaryResponse> findAll(Pageable pageable) {
        return postRepository.findAllWithMember(pageable)
                .map(PostDto.SummaryResponse::from);
    }

    /* ── 단건 조회 ─────────────────────────────────── */
    public PostDto.Response findById(Long id) {
        Post post = getPostEntity(id);
        return PostDto.Response.from(post);
    }

    /* ── 등록 ──────────────────────────────────────── */
    @Transactional
    public PostDto.Response create(PostDto.CreateRequest req) {
        Member member = memberService.getMemberEntity(req.memberId());
        Post post = Post.create(req.title(), req.content(), member);
        return PostDto.Response.from(postRepository.save(post));
    }

    /* ── 수정 ──────────────────────────────────────── */
    @Transactional
    public PostDto.Response update(Long id, PostDto.UpdateRequest req) {
        Post post = getPostEntity(id);
        post.update(req.title(), req.content());
        // @Transactional → 변경 감지(dirty checking)로 UPDATE 실행
        return PostDto.Response.from(post);
    }

    /* ── 삭제 ──────────────────────────────────────── */
    @Transactional
    public void delete(Long id) {
        Post post = getPostEntity(id);
        postRepository.delete(post);
    }

    /* ── 내부 헬퍼 ─────────────────────────────────── */
    private Post getPostEntity(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다. id=" + id));
    }
}
