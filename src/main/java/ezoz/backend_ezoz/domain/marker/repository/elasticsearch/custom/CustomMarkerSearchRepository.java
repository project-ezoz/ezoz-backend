package ezoz.backend_ezoz.domain.marker.repository.elasticsearch.custom;

import ezoz.backend_ezoz.domain.marker.entity.Marker;

import java.util.List;

public interface CustomMarkerSearchRepository {
    List<Marker> findByKeyword(String keyword);
}
