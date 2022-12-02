package ezoz.backend_ezoz.domain.marker.repository.elasticsearch;

import ezoz.backend_ezoz.domain.marker.entity.Marker;
import ezoz.backend_ezoz.domain.marker.repository.elasticsearch.custom.CustomMarkerSearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MarkerSearchRepository extends ElasticsearchRepository<Marker,Long>, CustomMarkerSearchRepository {
    List<Marker> findByContentContains(String keyword);
}
