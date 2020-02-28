package infra.kdbc.SpringBootPoc.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<Posts, Long> {
}
