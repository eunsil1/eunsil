package com.example.firstProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동생성 전략
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    //하나의 Article은 여러 Comment를 가진다
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    //mappedBy = "article" 이건 주인이 아니다라는 뜻
    //외래키는 Comment가 관리한다. 연관 관계의 주인은 Comment
    //CascadeType.REMOVE 연쇄 삭제
    //Article이 삭제 -> 연결된 Comment 전부 삭제
    //없으면 댓글이 남음 -> 외래키 오류가 생김

    //양방향 연관관계를 맺으면 양쪽에서 조회 가능
    //article.getComments()
    //comment.getArticle()


    public void patch(Article article) {
        if (article.title != null){
            this.title = article.title;
        }
        if (article.content != null){
            this.content = article.content;
        }
    }
}
