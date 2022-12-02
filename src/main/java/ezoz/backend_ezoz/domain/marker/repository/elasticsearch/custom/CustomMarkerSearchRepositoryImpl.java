package ezoz.backend_ezoz.domain.marker.repository.elasticsearch.custom;

import ezoz.backend_ezoz.domain.marker.entity.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomMarkerSearchRepositoryImpl implements CustomMarkerSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Marker> findByKeyword(String keyword) {
        Criteria criteria = Criteria.where("title").contains(keyword)
                .or(Criteria.where("content").contains(keyword))
                .or(Criteria.where("author").contains(keyword));

        SearchHits<Marker> search = elasticsearchOperations.search(new CriteriaQuery(criteria), Marker.class);

        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }
}
