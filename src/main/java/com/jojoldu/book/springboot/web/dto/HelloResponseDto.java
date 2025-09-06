package com.jojoldu.book.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



//@Getter - 선언된 모든 필드에 get 메소드 생성
//@RequiredArgsConstructor - 선언된 모든 final 필드가 포함된 생성자를 생성
//- final이 없는 필드는 생성자에 포함되지 않는다
@Getter
// @RequiredArgsConstructor // 생성자를 자동생성해주지만 기본 생성자는 없음
@AllArgsConstructor // 모든 필드를 포함한 생성자 생성, final이든 아니든, 
@NoArgsConstructor // 파라미터 없는 생성자 생성
public class HelloResponseDto {
    private  String name;
    private int amount;


}
