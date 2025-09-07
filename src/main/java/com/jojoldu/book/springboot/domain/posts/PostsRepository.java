package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRepository extends JpaRepository<Posts,Long>{
    //기본적인 CRUD 메소드가 자동으로 생성됨
    //save(), findById(), findAll(),delete() 등
    // Entity 클래스는 이 레포지토리없이는 제대로 역할을 할 수가 없음
}
