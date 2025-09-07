package com.jojoldu.book.springboot.domain.posts;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//스프링 부트의 전체 컨텍스트를 로드하고 테스트를 수행
// DB JPA레포지토리 서비스 컨트롤러 등 전체 환경에서 통합 테스트 가능
// WebMvcTest는 웹 계층만 테스트 웹 관련 빈만 로딩
// 별다른 설정 없이 @SpringBootTest를 사용할 경우 H2 데이터베이스를 자동으로 실행해줌
@SpringBootTest
class PostsRepositoryTest {

    //필드 및 의존성 주입 테스트 코드 내에서 DB 조작 가능하게 해줌
    @Autowired
    PostsRepository postsRepository;

    //AfterEach는 각 테스트 메서드 실행 후 호출되는 메서드
    // 테스트용 DB를 깨끗하게 초기화
    @AfterEach
    void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장 후 불러오기 테스트")
    void 게시글저장_불러오기(){

        //given
        String title = "테스트 게시글";
        String cotent = "테스트 본문";

        postsRepository.save(Posts.builder() // .save() -> posts 테이블에 insert/update 쿼리를 실행
                .title(title)
                .content(cotent)
                .author("jojoldu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); 

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(cotent);
    }
}
