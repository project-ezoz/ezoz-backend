package ezoz.backend_ezoz.api.marker.dto;

import ezoz.backend_ezoz.domain.marker.entity.Location;
import ezoz.backend_ezoz.domain.marker.entity.Marker;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@ApiModel(value = "마커 조회 API 응답 값", description = "마커 조회에 따른 응답 값입니다.")
public class MarkerDetailDto {

    @ApiModelProperty(value = "마커 id", example = "1")
    private Long markerId;

    @ApiModelProperty(value = "마커 위도, 예) 36.2155491", required = true)
    private String latitude;

    @ApiModelProperty(value = "마커 경도, 예) 127.487786", required = true)
    private String longitude;

    @ApiModelProperty(value = "마커 주소", example = "충청남도 금산군 추부면")
    private String address;
    
    @ApiModelProperty(value = "마커 제목", example = "무언가를 두고 오고 싶을 때 가고 싶은 곳")
    private String title;

    @ApiModelProperty(value = "마커 내용", example = "Lorem ipsum dolor sit amet...")
    private String content;

    @ApiModelProperty(value = "마커 저자", example = "전성은")
    private String author;

    @ApiModelProperty(value = "S3의 해당 마커 이미지 key 값이 담긴 리스트")
    private List<String> markerImageUrls;

    public static MarkerDetailDto of(Marker marker, List<String> markerImageUrls) {

        Location location = marker.getLocation();

        return MarkerDetailDto.builder()
                .markerId(marker.getMarkerId())
                .latitude(location.getLongitude())
                .longitude(location.getLongitude())
                .address(location.getAddress())
                .title(marker.getTitle())
                .content(marker.getContent())
                .author(marker.getAuthor())
                .markerImageUrls(markerImageUrls)
                .build();
    }
}
