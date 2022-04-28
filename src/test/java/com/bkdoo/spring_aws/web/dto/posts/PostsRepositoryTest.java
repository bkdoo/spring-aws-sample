package com.bkdoo.spring_aws.web.dto.posts;

import com.bkdoo.spring_aws.domain.posts.Posts;
import com.bkdoo.spring_aws.domain.posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    // save, findAll 기능 테스트

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void loadSavedContent(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("bkdoo@lgcns.com").build());
        //테이블 posts에 insert/update 쿼리를 실행하며, id가 있다면 update, 없다면 insert가 실행됨

        //when
        List<Posts> postsList = postsRepository.findAll(); //테이블 posts에 있는 모든 데이터를 조회해오는 메소드.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        //테이블에 한건 넣고 다시 불러왔을때 내가 넣은애랑 같은지 확인

        //별다른 설정없이 @SpringBootTest를 사용하라 경우 H2 데이터베이스를 자동으로 실행해줌

        //실행된 쿼리를 로그로 보고싶냐? 스프링 부트에서는 application.properties, application.yml 등의 파일로 한줄 코드로 설정가능
    }
}
