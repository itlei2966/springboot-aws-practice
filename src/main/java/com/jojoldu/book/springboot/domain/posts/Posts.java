package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor // 위 두개는 lombok의 어노테이션 이며 코드를 단순화시켜 주지만 필수는 아님
@Entity //JPA의 어노테이션으로 테이블과 링크될 클래스임을 나타냄 카멜케이스 이름을 언더스코어 네이밍으로 테이블이랑 매칭
        // SalesManager.java -> sales_manager table과 매칭
public class Posts extends BaseTimeEntity { // 실제 DB의 테이블과 매칭될 클래스 Entity 클래스라고 함

    
    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙을 나타냄 auto_increment 해줌
    private Long id;

    //테이블의 Column을 나타냄 굳이 선언 안해도 되지만 기본값 외의 변경의 필요한 옵션이 있다면 사용
    @Column(length = 500, nullable = false) 
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //해당 클래스의 빌더 패턴 클래스를 생서으 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    //Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
    // 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 함
    // 무분별하게 상태 변경시 데이터 무결성이 깨질 수 있음
    // Setter 없이 어떻게 값을 채워 DB에 삽입하나?
    // 빌더 클래스를 사용 - 생성시점에 값을 채워 줌 생성자의 경우 채워야 할 필드가 무엇인지 명확히 지정할 수 없음
    //

    public void update(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}
