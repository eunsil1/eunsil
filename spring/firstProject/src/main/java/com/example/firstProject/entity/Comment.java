package com.example.firstProject.entity;

import com.example.firstProject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //댓글 여러개 - 게시글 하나
    @JoinColumn(name="article_Id") //article_id라는 이름의 외래키 컬럼 생성
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        //예외발생
        if (dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야함");
        }
        if (dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        }
        //엔티티 생성 및 반환
//        return new Comment(
//                dto.getId(),
//                article,
//                dto.getNickname(),
//                dto.getBody()
//        );

        return Comment.builder()
                .id(dto.getId())
                .article(article)
                .nickname(dto.getNickname())
                .body(dto.getBody())
                .build();
    }

    public void patch(CommentDto dto) {
        //예외발생
        if(this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id 입력");
        }
        //객체 갱신
        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null){
            this.body = dto.getBody();
        }
    }

    //Comment comment = new Comment(1L, article, "홍길동", "내용");
//    Comment comment = Comment.builder()
//            .id(1L)
//            .article(article)
//            .nickname("홍길동")
//            .body("내용")
//            .build();
    //1. 순서 상관없음
    //2. 필요한 값만 넣기 가능
    //3. 가독성 좋음

}
