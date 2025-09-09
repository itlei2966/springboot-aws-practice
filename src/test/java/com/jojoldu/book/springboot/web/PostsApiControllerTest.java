package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//WebMvcTest의 경우 JPA 기능이 작동하지 않기 때문에 사용 x
//실제 HTTP 요청처럼 테스트 가능 SpringBootTest.WebEnvironment.RANDOM_PORT
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    
    //랜덤 포트를 가져옴
    @LocalServerPort
    private int port;

    //HTTP 요청을 보내는 테스트용 객체
    @Autowired
    private TestRestTemplate restTemplate;

    //테스트 후 DB 확인용
    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    void tearDown() throws Exception{
        postsRepository.deleteAll();
    }


    //실제 api 요청과 DB 저장 검증 테스트
    @Test
    void Posts_등록된다() throws Exception{

        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        //테스트에 사용할 dto 생성

        //요청 url 생성
        String url = "http://localhost:"+port + "/api/v1/posts";

        //when
        //HTTP POST 요청을 보냄서 응답을 받음
        ResponseEntity<Long> responseEntity = restTemplate.
                postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }


    @Test
    void Posts_수정된다() throws Exception
    {

        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port +"/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        //HttpEntity<T> - HTTP 요청이나 응답의 본문과 헤더를 감싸는 클래스
        // -> 위 내용은 PostsUpdateRequestDto를 HTTP 요청의 body에 담겠다는 의미


        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url,
            HttpMethod.PUT,requestEntity, Long.class);
        // url, HTTP메서드, 요청 본문 및 헤더, 응답을 어떤 타입으로 받을지

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}
