package com.example.roomfit.service;

import com.example.roomfit.repository.CommentRepository;
import com.example.roomfit.repository.InteriorPostRepository;
import com.example.roomfit.repository.PostLikeRepository;
import com.example.roomfit.domain.*;
import com.example.roomfit.dto.InteriorPostFormDto;
import com.example.roomfit.exception.BusinessException;
import com.example.roomfit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InteriorPostService {

    private final InteriorPostRepository interiorPostRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final FileStorageService fileStorageService;

    //스타일 필터 조건에 따라 게시글 목록 조회
    public Page<InteriorPost> list(InteriorStyle style, Pageable pageable){
        if(style == null){
            return interiorPostRepository.findByStatus(PostStatus.VISIBLE, pageable);
        }
        return interiorPostRepository.findByStatusAndStyle(PostStatus.VISIBLE, style, pageable);
    }

    //인테리어 목록 페이지용
    @Transactional(readOnly = true)
    public Page<InteriorPost> listForView(InteriorStyle style, Pageable pageable){
        Page<InteriorPost> page = list(style, pageable);
        page.getContent().forEach(InteriorPost::getThumbnailPath);
        return page;
        //list + getThumbnailPath()
    }

    @Transactional(readOnly = true)
    public java.util.List<InteriorPost> listPopularForView(int limit) {
        java.util.List<InteriorPost> posts = interiorPostRepository
                .findTop20ByStatusOrderByLikeCountDescViewCountDescCreatedAtDesc(
                        PostStatus.VISIBLE, org.springframework.data.domain.PageRequest.of(0, limit));
        posts.forEach(InteriorPost::getThumbnailPath);
        return posts;
        //인기 n건
    }


    //메인 인기글
    @Transactional(readOnly = true)
    public java.util.List<InteriorPost> listRecentForView(int limit) {
        java.util.List<InteriorPost> posts = interiorPostRepository
                .findTop50ByStatusOrderByCreatedAtDesc(PostStatus.VISIBLE);
        posts.forEach(InteriorPost::getThumbnailPath);
        //화면 넘기기 넘기기전에 썸네일 경로를 확정하고, 세션밖 오류를 막는것
        return posts.stream().limit(limit).toList();
    }
    //최신 n건

    /**
     * 상세 화면용: 조회수 증가·댓글·좋아요 여부를 한 트랜잭션에서 처리합니다.
     * (open-in-view=false 환경에서 Thymeleaf 렌더 전에 연관 데이터를 로드)
     */
    @Transactional
    public InteriorPostDetailView loadDetailPage(Long id, Long memberId) {
        InteriorPost post = interiorPostRepository.findByIdAndStatus(id, PostStatus.VISIBLE)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다."));
        //노출(visible) 글만 (images, author)
        post.increaseViewCount(); //조회 수 + 1
        post.getAuthor().getNickname(); //작성자 로드(Lazy)
        post.getThumbnailPath();//이미지 썸네일 경로계산
        List<Comment> comments = //인테리어 댓글 목록(author, parent)
                commentRepository.findByPostTypeAndPostIdAndStatusOrderByCreatedAtAsc(
                        PostType.INTERIOR, id, PostStatus.VISIBLE);
        boolean liked = isLiked(id, memberId); //로그인 시 좋아요 여부(member null이면 false)
        return new InteriorPostDetailView(post, comments, liked);
    }

    public record InteriorPostDetailView(
            InteriorPost post,
            List<Comment> comments,
            boolean liked) {
    }
    //상세 화면에 필요한 데이터를 한 묶음으로 컨트롤러에 전달

    public boolean isLiked(Long postId, Long memberId) {
        return memberId != null && postLikeRepository.existsByMemberIdAndPostId(memberId, postId);
    }

    //글쓰기
    @Transactional
    public Long create(Long memberId, InteriorPostFormDto dto, MultipartFile image) throws IOException {
        Member author = memberService.findById(memberId);
        InteriorPost post = InteriorPost.builder()
                .author(author)
                .style(dto.getStyle())
                .title(dto.getTitle())
                .content(dto.getContent())
                .roomSize(dto.getRoomSize())
                .budget(dto.getBudget())
                .hasFurnitureTag(dto.isHasFurnitureTag())
                .status(PostStatus.VISIBLE)
                .build();
        String path = fileStorageService.storeInteriorImage(image);
        if (path != null) { //이미지가 있으면
            post.addImage(PostImage.builder().filePath(path).thumbnail(true).sortOrder(0).build());
        }//postImage 1장 - 대표이미지
        return interiorPostRepository.save(post).getId();
    }

    //수정
    @Transactional
    public void update(Long postId, Long memberId, InteriorPostFormDto dto, MultipartFile image) throws IOException {
        InteriorPost post = getOwnedPost(postId, memberId);
        post.setStyle(dto.getStyle());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setRoomSize(dto.getRoomSize());
        post.setBudget(dto.getBudget());
        post.setHasFurnitureTag(dto.isHasFurnitureTag());
        post.setUpdatedAt(LocalDateTime.now());
        if (image != null && !image.isEmpty()) {
            String path = fileStorageService.storeInteriorImage(image);
            post.getImages().clear(); //기존 이미지 삭제
            post.addImage(PostImage.builder().filePath(path).thumbnail(true).build());
            //새이미지 넣는다
        }
    }

    //본인 작성한 글 수정
    private InteriorPost getOwnedPost(Long postId, Long memberId) {
        InteriorPost post = interiorPostRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다."));
        if (!post.getAuthor().getId().equals(memberId)) {
            throw new BusinessException("수정 권한이 없습니다.");
        }
        return post;
    }

    //삭제
    @Transactional
    public void delete(Long postId, Long memberId) {
        InteriorPost post = getOwnedPost(postId, memberId); //본인 확인
        post.setStatus(PostStatus.DELETED); //소프트삭제 status
    }

    //로그인 토글
    @Transactional
    public boolean toggleLike(Long postId, Long memberId) {
        InteriorPost post = interiorPostRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다."));
        var existing = postLikeRepository.findByMemberIdAndPostId(memberId, postId);
        if (existing.isPresent()) {
            postLikeRepository.delete(existing.get());
            post.setLikeCount(Math.max(0, post.getLikeCount() - 1));
            return false;
        }
        Member member = memberService.findById(memberId);
        postLikeRepository.save(PostLike.builder().member(member).post(post).build());
        post.setLikeCount(post.getLikeCount() + 1); //없으면 + 1
        return true;
    }

    //댓글 작성
    @Transactional
    public void addComment(Long postId, Long memberId, String content, Long parentId) {
        InteriorPost post = interiorPostRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다."));
        Member author = memberService.findById(memberId);
        var comment = com.example.roomfit.domain.Comment.builder()
                .postType(PostType.INTERIOR)
                .postId(postId)
                .author(author)
                .content(content)
                .build();
        if (parentId != null) { //대댓글
            comment.setParent(commentRepository.findById(parentId).orElse(null));
        }
        commentRepository.save(comment);
        post.setCommentCount((int) commentRepository.countByPostTypeAndPostIdAndStatus(
                PostType.INTERIOR, postId, PostStatus.VISIBLE));
    }

    //특정 게시물에 대한 댓글 목록 조회
    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostTypeAndPostIdAndStatusOrderByCreatedAtAsc(
                PostType.INTERIOR, postId, PostStatus.VISIBLE);
    }
    //findByPostTypeAndPostIdAndStatusOrderByCreatedAtAsc
    //인테리어 게시글 정상(visible) 댓글들을 오래된 순서로 가져온다
}
