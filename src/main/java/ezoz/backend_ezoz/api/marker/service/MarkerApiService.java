package ezoz.backend_ezoz.api.marker.service;

import ezoz.backend_ezoz.api.marker.dto.MarkerDetailDto;
import ezoz.backend_ezoz.api.marker.dto.MarkerDto;
import ezoz.backend_ezoz.api.marker.dto.UpdateMarkerDto;
import ezoz.backend_ezoz.domain.marker.entity.Location;
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
import org.springframework.util.StringUtils;
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
            String imageUrl = fileService.getImageUrl(markerImage.getStoreFileName());
            markerImageUrls.add(imageUrl);
        }

        return MarkerDetailDto.of(marker, markerImageUrls);
    }

    public void editMarker(UpdateMarkerDto updateMarkerDto) {

        Marker marker = markerService.findByIdFetchImage(updateMarkerDto.getMarkerId());

        updateMarker(marker, updateMarkerDto);

    }

    private void updateMarker(Marker marker, UpdateMarkerDto updateMarkerDto) {

        Location location = marker.getLocation();
        if (StringUtils.hasText(updateMarkerDto.getCoordinate())) {
            location.updateCoordinate(updateMarkerDto.getCoordinate());
        }
        if (StringUtils.hasText(updateMarkerDto.getAddress())) {
            location.updateAddress(updateMarkerDto.getAddress());
        }

        if (StringUtils.hasText(updateMarkerDto.getTitle())) {
            marker.updateTitle(updateMarkerDto.getTitle());
        }

        if (StringUtils.hasText(updateMarkerDto.getContent())) {
            marker.updateContent(updateMarkerDto.getContent());
        }

        List<MarkerImage> originalMarkerImages = marker.getMarkerImages();
        List<MultipartFile> updateMarkerImageFiles = updateMarkerDto.getMarkerImageFiles();

        // 추가된 파일 확인하기
        for (MultipartFile updateMarkerImageFile : updateMarkerImageFiles) {
            boolean isExists = false;
            String updateFileName = updateMarkerImageFile.getName();
            for (MarkerImage originalMarkerImage : originalMarkerImages) {
                if (updateFileName.equals(originalMarkerImage)) {
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                try {
                    UploadFile uploadFile = fileService.storeFile(updateMarkerImageFile);
                    originalMarkerImages.add(
                            new MarkerImage(uploadFile.getOriginalFileName(), uploadFile.getStoreFileName()));
                } catch (IOException e) {
                    throw new BusinessException(ErrorCode.FAILED_REGISTER_IMAGE);
                }
            }
        }

        // 제거된 파일 확인하기
        for (MarkerImage originalMarkerImage : originalMarkerImages) {
            boolean isExists = false;
            String originalFileName = originalMarkerImage.getOriginalFileName();
            for (MultipartFile updateMarkerImageFile : updateMarkerImageFiles) {
                if (originalFileName.equals(updateMarkerImageFile.getName())) {
                    isExists = true;
                    break;
                }
                if (!isExists) {
                    fileService.removeImage(originalMarkerImage.getStoreFileName());
                    originalMarkerImages.remove(originalMarkerImage);
                }
            }
        }


    }
}
