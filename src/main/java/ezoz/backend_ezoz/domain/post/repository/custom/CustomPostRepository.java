package ezoz.backend_ezoz.domain.post.repository.custom;

import ezoz.backend_ezoz.domain.post.entity.Post;

import java.util.Optional;

public interface CustomPostRepository {
    Optional<Post> findByIdFetchImage(Long postId);
}
