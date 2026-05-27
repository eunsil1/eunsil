package com.example.boardloginimgsecurityoauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

@Configuration  //스프링 빈에 등록

//시큐리티 7에서는 LoginCheckInterceptor 여기서 안써줘도 됨
public class WebConfig implements WebMvcConfigurer {
//    private final LoginCheckInterceptor loginCheckInterceptor;

    @Value("${app.upload-dir}")
    private String uploadDir;
    //application.properties 에서 파일관리

//    public WebConfig(LoginCheckInterceptor loginCheckInterceptor) {
//        this.loginCheckInterceptor = loginCheckInterceptor;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path path = Path.of(uploadDir).toAbsolutePath().normalize();
        //상대경로 ./upload -> c:/project/upload
        String location = path.toUri().toString();
        if (!location.endsWith("/")) {
            location += "/";
        }
        registry.addResourceHandler("/uploads/**") //브라우저가 요청하는 가상주소
                .addResourceLocations(location); //실제 물리적주소
    }
    // /uploads/ 접근했을때 연결을C:\Users\tj\board-login-img-security-uploads



//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginCheckInterceptor)
//                .addPathPatterns("/posts/write", "/posts/*/edit", "/posts/*/delete");
//    }
}
//   /post   로그인 필요없음
//   /posts/{id} 로그인필요없음
//"/posts/write", "/posts/1/edit", "/posts/2/delete 로그인필요

