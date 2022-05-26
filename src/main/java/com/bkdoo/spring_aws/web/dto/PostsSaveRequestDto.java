package com.bkdoo.spring_aws.web.dto;

import com.bkdoo.spring_aws.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }

}

// 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안됨.
// Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이기 때문
// Entity 클래스가 변경되면 여러 클래스에 영향을 끼치지만, Request와 Response용 Dto는 View를 위한 클래스라 자주 변경이 필요함
// View Layer와 DB Layer의 역할 분리를 철저하게 해야함.
// 따라서 Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야함.
