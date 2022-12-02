package ezoz.backend_ezoz.domain.marker.repository.custom;

import ezoz.backend_ezoz.domain.marker.entity.Marker;

import java.util.Optional;

public interface CustomMarkerRepository {
    Optional<Marker> findByIdFetchImage(Long postId);
}
