package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts,Long>{
    //기본적인 CRUD 메소드가 자동으로 생성됨
    //save(), findById(), findAll(),delete() 등
    // Entity 클래스는 이 레포지토리없이는 제대로 역할을 할 수가 없음

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
    // SprignDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 되는 것을 보여주기 위해 사용
    // SpringDataJpa에서 제공하는 기본 메소드만으로도 가능

    // List<Posts> findAllByOrderByIdDesec();
}
