package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


//DTO를 받아서 Service를 호출하는 역할만 하고 있음
@RequiredArgsConstructor
@RestController
public class PostsApiController  {
    //final 필드인 postsService를 자동주입
    private final PostsService postsService;

    //post 생성
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    //post 수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }
    //@PathVariable - url 경로에 있는 값을  가져오는 것 url의 id 부분이 해당함
    //@RequestBody - 요청 본문에 담긴 JSON 같은 데이터를 객체로 변환 하는 것

    //post 조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

}
