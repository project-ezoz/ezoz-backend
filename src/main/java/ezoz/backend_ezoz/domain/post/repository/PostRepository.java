package ezoz.backend_ezoz.domain.post.repository;

import ezoz.backend_ezoz.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
