package ezoz.backend_ezoz.domain.post.repository.custom;

import ezoz.backend_ezoz.domain.post.entity.Post;

import java.util.List;

public interface CustomPostRepository {
    List<Post> findByKeyword(String keyword);

}
