package infra.kdbc.SpringBootPoc.domain;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostsRepoTest {

    @Autowired
    PostsRepo postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void post_load() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("test@test.com")
                .build());

        //when
        Optional<Posts> post = postsRepository.findById(1L);

        //then
        System.out.println(post);
        //assertEquals(title,post.get());
        //assertEquals(content,post.getContent());
    }
}