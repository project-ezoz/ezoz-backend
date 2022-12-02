package ezoz.backend_ezoz.domain.marker.repository;

import ezoz.backend_ezoz.domain.marker.entity.Marker;
import ezoz.backend_ezoz.domain.marker.repository.custom.CustomMarkerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long>, CustomMarkerRepository {

}
