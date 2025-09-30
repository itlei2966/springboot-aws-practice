package com.jojoldu.book.springboot.config.auth;


import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain sercurityFilterChain(HttpSecurity http) throws Exception{
        http
                //h2-console 접근 허용을 위한 설정
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                //요청 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated()
                )

                //로그아웃 설정
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )

                //OAuth2 로그인 설정
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                ); //로그인 기능 활성화
                    // userInfoEndpoint : 로그인 후 사용자 정보(UserInfo)를 가져올 떄의 설정
                    // 사용자 정보를 가져오는 과정에서 customOAuth2UserService를 호출하도록 연결
                    //

        return http.build();

    }

}
