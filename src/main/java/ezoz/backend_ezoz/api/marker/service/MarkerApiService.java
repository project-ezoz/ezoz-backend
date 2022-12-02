package ezoz.backend_ezoz.api.marker.service;

import ezoz.backend_ezoz.api.marker.dto.MarkerDetailDto;
import ezoz.backend_ezoz.api.marker.dto.MarkerDto;
import ezoz.backend_ezoz.domain.marker.entity.Marker;
import ezoz.backend_ezoz.domain.marker.entity.MarkerImage;
import ezoz.backend_ezoz.domain.marker.service.MarkerService;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.jwt.TokenManager;
import ezoz.backend_ezoz.infra.FileService;
import ezoz.backend_ezoz.infra.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkerApiService {

    private final MarkerService markerService;
    private final FileService fileService;
    private final TokenManager tokenManager;

    public Long registerMarker(MarkerDto.Request markerRequestDto) {

        List<MarkerImage> markerImages = new ArrayList<>();
        registerPostImages(markerImages, markerRequestDto.getMarkerImageFiles());

        Marker marker = markerRequestDto.toEntity("ckdgus", markerImages);

        return markerService.save(marker);
    }

    private void registerPostImages(List<MarkerImage> markerImages, List<MultipartFile> multipartFiles) {

        for (MultipartFile multipartFile : multipartFiles) {
            try {
                UploadFile uploadFile = fileService.storeFile(multipartFile);
                markerImages.add(uploadFile.createPostImage());
            } catch (IOException e) {
                throw new BusinessException(ErrorCode.FAILED_REGISTER_IMAGE, e);
            }
        }

    }

    public List<MarkerDto.Response> searchByKeyword(String keyword) {
        return markerService.searchByKeyword(keyword).stream()
                .map(MarkerDto.Response::from)
                .collect(Collectors.toList());
    }

    public MarkerDetailDto getMarkerDetail(Long postId) {

        Marker marker = markerService.findByIdFetchImage(postId);

        List<String> markerImageUrls = new ArrayList<>();
        for (MarkerImage markerImage : marker.getMarkerImages()) {
            String originalFileName = markerImage.getOriginalFileName();
            String imageUrl = fileService.getImageUrl(originalFileName);
            markerImageUrls.add(imageUrl);
        }

        return MarkerDetailDto.of(marker, markerImageUrls);

    }
}
