package ezoz.backend_ezoz.domain.post.repository.elasticsearch.custom;

import ezoz.backend_ezoz.domain.post.entity.Post;

import java.util.List;

public interface CustomPostSearchRepository {
    List<Post> findByKeyword(String keyword);
}
