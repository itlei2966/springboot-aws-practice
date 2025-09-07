package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//DTO를 받아서 Service를 호출하는 역할만 하고 있음
@RequiredArgsConstructor
@RestController
public class PostsApiController  {
    //final 필드인 postsService를 자동주입
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}
