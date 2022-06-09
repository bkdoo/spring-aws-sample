package com.bkdoo.spring_aws.web;

import com.bkdoo.spring_aws.config.auth.dto.SessionUser;
import com.bkdoo.spring_aws.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
    // index.mustache와 마찬가지로 /posts/save를 호출하면 posts-save.mustache를 호출하는 메소드

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName", user.getName());
        } else {
            System.out.println("d");
        }
        return "index";
    }
    // model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장, 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달함.

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(Model model, @PathVariable Long id) {

        model.addAttribute("post", postsService.findById(id));
        return "posts-update";
    }
    // @PathVariable
}

// 머스터치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의파일 확장자는 자동으로 지정됨
// 앞의 경로는 src/main/resources/templates, 확장자는 .mustache
// 여기선 index를 반환하므로, src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리하게 됨.
