package com.example.board.controller;

import com.example.board.dto.PostDto;
import com.example.board.exception.ResourceNotFoundException;
import com.example.board.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean  PostService postService;

    @Test
    @DisplayName("GET /api/posts → 200 OK + 페이지 응답")
    void getAll_returns200() throws Exception {
        PostDto.SummaryResponse summary = new PostDto.SummaryResponse(
                1L, "제목", "작성자", LocalDateTime.now());
        given(postService.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(summary)));

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("제목"));
    }

    @Test
    @DisplayName("GET /api/posts/999 → 404 Not Found")
    void getById_notFound_returns404() throws Exception {
        given(postService.findById(999L))
                .willThrow(new ResourceNotFoundException("게시글을 찾을 수 없습니다. id=999"));

        mockMvc.perform(get("/api/posts/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    @DisplayName("POST /api/posts — 제목 blank → 400 Bad Request")
    void create_blankTitle_returns400() throws Exception {
        String body = objectMapper.writeValueAsString(
                new PostDto.CreateRequest("", "내용", 1L));

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.title").exists());
    }

    @Test
    @DisplayName("DELETE /api/posts/1 → 204 No Content")
    void delete_returns204() throws Exception {
        willDoNothing().given(postService).delete(1L);

        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isNoContent());
    }
}
