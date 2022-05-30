package com.bkdoo.spring_aws.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // ibatis나 Mybatis 등에서 Dao라고 불리는 DB Layer 접근자입니다.
    // JPA에서는 Repository 라고 부르며 인터페이스로 생성함
    // JpaRepository<Entity 클래스, PK 타입> 을 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨
    // @Repository를 추가할 필요도 없으나, Entity클래스와 기본 Entity Repository는 함께 위치해야함

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
