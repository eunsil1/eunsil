package com.example.boardloginimg.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private User author;


    @Column(nullable = false)
    private LocalDateTime createdAt;


    //1:N 관계 게시글 하나에 여러 이미지를 가지며,
    //이미지의 생성/삭제 - 게시글이 같이 책임진다.
    //연관관게의 주인 BoardImage
    //cascade = CascadeType.ALL board 저장 /삭제 -> 이미지도 같이 처리
    //boardRepository.save(board) -> Board 저장 + BoardImage들도 같이 insert
    //orphanRemoval = true Board 에서 이미지가 제거되면 DB에서도 삭제

    @OneToMany(mappedBy = "board" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardImage> images = new ArrayList<>();

    public List<BoardImage> getImages() {
        return images;
    }

    public Board() {
    }

    public Board(String title, String content, User author, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}
