package com.bkdoo.spring_aws.web;


import com.bkdoo.spring_aws.service.posts.PostsService;
import com.bkdoo.spring_aws.web.dto.PostUpdateRequestDto;
import com.bkdoo.spring_aws.web.dto.PostsResponseDto;
import com.bkdoo.spring_aws.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }
}

// 스프링에서 Bean을 주입받는 방식 1) Autowired 2) setter 3) 생성자
// 이 중 가장 권장하는 방식은 생성자, Autowired는 권장하지 않음
// @RequiredArgsConstructor에서 생성자를 생성함 - final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성함
// lombok기능을 사용하는 이유는 해당 클래스의 의존성 관꼐가 변경될 때 마다 생성자 코드를 계속해서 수정해야하기 때문