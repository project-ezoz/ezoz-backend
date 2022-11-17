package ezoz.backend_ezoz.domain.post.repository.elasticsearch;

import ezoz.backend_ezoz.domain.post.entity.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostSearchRepository extends ElasticsearchRepository<Post,Long> {
    List<Post> findByContentContains(String keyword);
}
