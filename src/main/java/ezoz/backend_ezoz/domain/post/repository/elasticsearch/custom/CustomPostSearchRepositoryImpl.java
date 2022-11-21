package ezoz.backend_ezoz.domain.post.repository.elasticsearch.custom;

import ezoz.backend_ezoz.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomPostSearchRepositoryImpl implements CustomPostSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;


    @Override
    public List<Post> findByKeyword(String keyword) {
        Criteria criteria = Criteria.where("title").contains(keyword)
                .or(Criteria.where("content").contains(keyword))
                .or(Criteria.where("author").contains(keyword));

        SearchHits<Post> search = elasticsearchOperations.search(new CriteriaQuery(criteria), Post.class);

        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }
}
