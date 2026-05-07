package com.example.boardlogin.support;

import com.example.boardlogin.domain.Board;
import com.example.boardlogin.domain.User;
import com.example.boardlogin.web.dto.BoardForm;

import java.time.LocalDateTime;

public class BoardMapper {
    public BoardMapper() {
    }

    public static Board toNewEntity(BoardForm form, User author, LocalDateTime createdAt){
        return new Board(form.getTitle(), form.getContent(), author, createdAt);
    }
}
