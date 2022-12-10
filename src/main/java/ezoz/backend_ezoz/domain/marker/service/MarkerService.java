package ezoz.backend_ezoz.domain.marker.service;

import ezoz.backend_ezoz.domain.marker.entity.Marker;
import ezoz.backend_ezoz.domain.marker.repository.MarkerRepository;
import ezoz.backend_ezoz.domain.marker.repository.elasticsearch.MarkerSearchRepository;
import ezoz.backend_ezoz.global.error.exception.EntityNotFoundException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarkerService {

    private final MarkerRepository markerRepository;
    private final MarkerSearchRepository markerSearchRepository;

    @Transactional
    public Long save(Marker marker) {

        Marker savedMarker = markerRepository.save(marker);
        markerSearchRepository.save(savedMarker);

        return savedMarker.getMarkerId();
    }

    public List<Marker> searchByKeyword(String keyword) {
        return markerSearchRepository.findByKeyword(keyword);
    }

    public Marker findByIdFetchImage(Long markerId) {
        return markerRepository.findByIdFetchImage(markerId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MARKER_NOT_EXISTS));
    }

    public Marker findByIdForES(Long markerId) {
        return markerSearchRepository.findById(markerId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MARKER_NOT_EXISTS));
    }

    public void saveEsEntity(Marker marker) {
        markerSearchRepository.save(marker);
    }

    public void deleteMarker(Long markerId) {
        markerRepository.deleteById(markerId);
        markerSearchRepository.deleteById(markerId);
    }

}
