package com.example.boardlogin.support;

import com.example.boardlogin.domain.User;
import com.example.boardlogin.web.dto.RegisterForm;
import com.example.boardlogin.web.dto.UserProfileDto;

public class UserMapper {

    public UserMapper() {
    }

    //회원가입 폼 -> 신규 엔티티
    public static User toNewEntity(RegisterForm form){
        return new User(form.getUsername(), form.getPassword(), form.getName());
    }

    //화면응답 : 엔티티 -> 노출용 엔티티
    public static UserProfileDto toProfileDto(User user){
        return new UserProfileDto(user.getUsername(), user.getName());
    }

}
