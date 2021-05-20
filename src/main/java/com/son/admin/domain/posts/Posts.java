package com.son.admin.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
    Getter -> 클래스 내 모든 필드의 Getter 메소드 자동생성
    id -> 해당 테이블의 PK 필드를 나타냄
    GeneratedValue -> PK 생성 규칙
    Column -> 테이블의 칼럼
    NoArgsConstructor -> 기본 생성자 자동 추가
    Builder -> 해달 클래스의 빌더 패턴 클래스 생성
 */

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
