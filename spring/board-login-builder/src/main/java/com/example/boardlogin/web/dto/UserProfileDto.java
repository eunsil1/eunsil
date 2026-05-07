package com.example.boardlogin.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileDto {

    private final String username;
    private final String name;
}
