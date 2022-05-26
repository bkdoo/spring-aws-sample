package com.bkdoo.spring_aws.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity //테이블과 링크될 클래스임을 나타냄
public class Posts {

    @Id //해당 테이블의 PK필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)//PK의 생성 규칙을 나타냄, Indentity > auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타내며 굳이 선언하지 않아도 모든 필드는 컬럼이됨
    private String title;

    @Column(columnDefinition ="Text", nullable = false) // 선언하고자 하는건 추가 옵션일때
    private String content;

    private String author;

    @Builder // 해당 클래서의 빌더 패턴 클래스를 생성함, 생성자 상단에 선언시 생성자에 포함된 빌드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    // Entity 클래서으세너느 절대 Setter 메소드를 만들지 않음
    // 대신 해당 필드 값의 변경이 필요하면 명확한 메소드를 추가해야함
    // 기본적인 구조는 생성자를 통해 최종값을 채운 후에 DB에 insert, 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출
    // 생성자 대신 @Builder를 통해 빌더 클래스 사용
    // 생성자나 빌더나 생성시점에 값을 채워주는 역할은 같음
    // 생성자의 경우 지금 채워야할 필드가 무엇인지 명확히 지정할 수 없음
}
