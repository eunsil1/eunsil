package com.example.board.service;

import com.example.board.dto.PostDto;
import com.example.board.entity.Member;
import com.example.board.entity.Post;
import com.example.board.exception.ResourceNotFoundException;
import com.example.board.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock PostRepository postRepository;
    @Mock MemberService  memberService;
    @InjectMocks PostService postService;

    Member testMember;

    @BeforeEach
    void setUp() {
        testMember = Member.create("user1", "pass", "닉네임1");
    }

    @Test
    @DisplayName("존재하지 않는 글 조회 시 ResourceNotFoundException 발생")
    void findById_notFound_throws404() {
        given(postRepository.findById(999L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> postService.findById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    @DisplayName("글 수정 시 title·content 가 변경된다")
    void update_changesFields() {
        Post post = Post.create("old title", "old content", testMember);
        given(postRepository.findById(1L)).willReturn(Optional.of(post));

        PostDto.UpdateRequest req = new PostDto.UpdateRequest("new title", "new content");
        PostDto.Response resp = postService.update(1L, req);

        assertThat(resp.title()).isEqualTo("new title");
        assertThat(resp.content()).isEqualTo("new content");
    }
}
