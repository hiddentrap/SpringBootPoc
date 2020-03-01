package infra.kdbc.SpringBootPoc.domain;

import lombok.extern.java.Log;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
class PostsRepoTest {

    @Autowired
    PostsRepo postsRepository;

    @Test
     void post_load() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        log.info("뭐냐뭐냐 시작");
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("test@test.com")
                .build());
        log.info("뭐냐뭐냐 끝");

        //when
        List<Posts> postsList = postsRepository.findAll();

        log.info("뭐냐뭐냐 끝끝끝끝");
        //then
        Posts posts = postsList.get(0);
        assertEquals(title,posts.getTitle());
        assertEquals(content,posts.getContent());
    }
}