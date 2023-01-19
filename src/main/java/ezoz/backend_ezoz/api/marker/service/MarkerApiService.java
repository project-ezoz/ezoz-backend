package ezoz.backend_ezoz.api.marker.service;

import ezoz.backend_ezoz.api.marker.dto.MarkerDetailDto;
import ezoz.backend_ezoz.api.marker.dto.MarkerDto;
import ezoz.backend_ezoz.api.marker.dto.UpdateMarkerDto;
import ezoz.backend_ezoz.domain.marker.entity.Location;
import ezoz.backend_ezoz.domain.marker.entity.Marker;
import ezoz.backend_ezoz.domain.marker.entity.MarkerImage;
import ezoz.backend_ezoz.domain.marker.service.MarkerService;
import ezoz.backend_ezoz.global.jwt.TokenManager;
import ezoz.backend_ezoz.infra.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkerApiService {

    private final MarkerService markerService;
    private final FileService fileService;
    private final TokenManager tokenManager;

    public Long registerMarker(MarkerDto.Request markerRequestDto) {

        Marker marker = markerRequestDto.toEntity("ckdgus");

        return markerService.save(marker);
    }

    public List<MarkerDto.Response> searchByKeyword(String keyword) {
        return markerService.searchByKeyword(keyword).stream()
                .map(MarkerDto.Response::from)
                .collect(Collectors.toList());
    }

    public MarkerDetailDto getMarkerDetail(Long markerId) {

        Marker marker = markerService.findByIdFetchImage(markerId);
        List<String> markerImageUrls = new ArrayList<>();
        for (MarkerImage markerImage : marker.getMarkerImages()) {
            String imageUrl = fileService.getImageUrl(markerImage.getMarkerImageKey());
            markerImageUrls.add(imageUrl);
        }

        return MarkerDetailDto.of(marker, markerImageUrls);
    }

    @Transactional
    public void updateMarker(Long markerId, UpdateMarkerDto updateMarkerDto) {

        Marker jpaMarker = markerService.findByIdFetchImage(markerId);
        Marker esMarker = markerService.findByIdForES(markerId);

        Location jpaLocation = jpaMarker.getLocation();
        Location esLocation = esMarker.getLocation();

        if (StringUtils.hasText(updateMarkerDto.getLatitude())) {
            jpaLocation.updateLatitude(updateMarkerDto.getLatitude());
            esLocation.updateLatitude(updateMarkerDto.getLatitude());
        }

        if (StringUtils.hasText(updateMarkerDto.getLongitude())) {
            jpaLocation.updateLongitude(updateMarkerDto.getLongitude());
            esLocation.updateLongitude(updateMarkerDto.getLongitude());
        }

        if (StringUtils.hasText(updateMarkerDto.getAddress())) {
            jpaLocation.updateAddress(updateMarkerDto.getAddress());
            esLocation.updateAddress(updateMarkerDto.getAddress());
        }

        if (StringUtils.hasText(updateMarkerDto.getTitle())) {
            jpaMarker.updateTitle(updateMarkerDto.getTitle());
            esMarker.updateTitle(updateMarkerDto.getTitle());
        }

        if (StringUtils.hasText(updateMarkerDto.getContent())) {
            jpaMarker.updateContent(updateMarkerDto.getContent());
            esMarker.updateContent(updateMarkerDto.getContent());
        }

        markerService.saveEsEntity(esMarker);
        updateMarkerImage(jpaMarker, updateMarkerDto.getMarkerImageKeys());

    }

    private void updateMarkerImage(Marker marker, List<String> updateMarkerImageKeys) {

        List<MarkerImage> originalMarkerImages = marker.getMarkerImages();
        Set<String> originalMarkerImagesSet = new HashSet<>();
        for (MarkerImage originalMarkerImage : originalMarkerImages) {
            originalMarkerImagesSet.add(originalMarkerImage.getMarkerImageKey());
        }

        Set<String> updateMarkerImageKeysSet = new HashSet<>(updateMarkerImageKeys);

        // 추가된 파일 확인하기
        for (String updateMarkerImageKey : updateMarkerImageKeysSet) {
            if (!originalMarkerImagesSet.contains(updateMarkerImageKey)) {
                MarkerImage updateMarkerImage = MarkerImage.builder()
                        .markerImageKey(updateMarkerImageKey)
                        .build();
                originalMarkerImages.add(updateMarkerImage);
            }
        }

        // 제거된 파일 확인하기
        for (MarkerImage originalMarkerImage : originalMarkerImages) {
            if (!updateMarkerImageKeysSet.contains(originalMarkerImage.getMarkerImageKey())) {
                originalMarkerImages.remove(originalMarkerImage);
            }
        }

    }

    public void deleteMarker(Long markerId) {
        Marker marker = markerService.findByIdFetchImage(markerId);

        deleteMarkerImage(marker.getMarkerImages());

        markerService.deleteMarker(markerId);
    }

    private void deleteMarkerImage(List<MarkerImage> markerImages) {
        for (MarkerImage markerImage : markerImages) {
            fileService.removeImage(markerImage.getMarkerImageKey());
        }
    }
}
