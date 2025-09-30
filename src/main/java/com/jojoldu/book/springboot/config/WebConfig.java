package com.jojoldu.book.springboot.config;


import com.jojoldu.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver
    loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(loginUserArgumentResolver);
    }

    // 커스텀 객체와 커스텀 어노테이션이 들어왔을 때
    // Spring이 인식할 수 있도록
    // Argument Resolver를 만들어서 Spring MVC에 등록해야 함
    // 그 역할을 하는 클래스

}
