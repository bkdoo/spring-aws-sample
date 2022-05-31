package com.bkdoo.spring_aws.service.posts;

import com.bkdoo.spring_aws.domain.posts.Posts;
import com.bkdoo.spring_aws.domain.posts.PostsRepository;
import com.bkdoo.spring_aws.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Posts posts  = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        postsRepository.save(posts);

        return id;
    }

    @Transactional
    public Long delete(Long id, PostsDeleteRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 개시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }
    //readOnly=true 를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨.
    //PostsListResponseDto::new 람다식 > postsRepository 결과로 넘어온 Poststs의 Stream을 map을 통해 PostsListResponseDto로 변환, List로 반환하는 메소드
}

// 스프링에서 Bean을 주입받는 방식 1) Autowired 2) setter 3) 생성자
// 이 중 가장 권장하는 방식은 생성자, Autowired는 권장하지 않음
// @RequiredArgsConstructor에서 생성자를 생성함 - final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성함
// lombok기능을 사용하는 이유는 해당 클래스의 의존성 관꼐가 변경될 때 마다 생성자 코드를 계속해서 수정해야하기 때문

