package com.jojoldu.book.springboot.web.dto;


import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @AllArgsConstructor // Builder 패턴 사용시 없어도 됨
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content,String author)
    {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    // Entity 클래스와 유사하지만 따로 생성한 이유는 Entity는 데이터베이스와 맞닿은 핵심 클래스이기 때ㅜㅁㄴ에
    // 막 사용하면 안돼서 여기에 따로 만들어 막 사용

}
