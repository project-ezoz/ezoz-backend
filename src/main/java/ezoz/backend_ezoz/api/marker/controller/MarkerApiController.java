package ezoz.backend_ezoz.api.marker.controller;

import ezoz.backend_ezoz.api.marker.dto.MarkerDetailDto;
import ezoz.backend_ezoz.api.marker.dto.MarkerDto;
import ezoz.backend_ezoz.api.marker.service.MarkerApiService;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.validator.ImageValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Api(tags = "Marker")
@RequiredArgsConstructor
public class MarkerApiController {

    private final MarkerApiService markerApiService;
    private final ImageValidator imageValidator;

    @PostMapping(value = "/marker", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "마커 생성 API", notes = "마커를 새로 등록한다.")
    public ResponseEntity<?> registerPost(MarkerDto.Request markerRequestDto, HttpServletRequest httpServletRequest) {

//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//        String accessToken = authorizationHeader.split(" ")[1];

        List<MultipartFile> markerImageFiles = markerRequestDto.getMarkerImageFiles();
        if (markerImageFiles == null || imageValidator.notExistFileName(markerImageFiles)) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS);
        }
        Long saveId = markerApiService.registerMarker(markerRequestDto);

        return ResponseEntity.ok(saveId);
    }

    @GetMapping("/marker")
    @ApiOperation(value = "마커 검색 API", notes = "키워드에 따라 추출된 마커를 제공한다.")
    public ResponseEntity<List<MarkerDto.Response>> searchByES(@RequestParam String keyword) {
        List<MarkerDto.Response> markerResponses = markerApiService.searchByKeyword(keyword);

        return ResponseEntity.ok(markerResponses);
    }

    @GetMapping("/marker/{markerId}")
    @ApiOperation(value = "마커 조회 API", notes = "마커 id를 통해 해당하는 마커를 제공한다.")
    public ResponseEntity<MarkerDetailDto> getMarkerDetail(@PathVariable Long markerId) {

        MarkerDetailDto markerDetail = markerApiService.getMarkerDetail(markerId);

        return ResponseEntity.ok(markerDetail);
    }
}
