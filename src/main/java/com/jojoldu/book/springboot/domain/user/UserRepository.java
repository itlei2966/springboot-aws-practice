package com.jojoldu.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // email을 통해 이미 생성된 사용자인지 ㅓㅊ음 가입하는지 판단 하기 위한 메소드
}
