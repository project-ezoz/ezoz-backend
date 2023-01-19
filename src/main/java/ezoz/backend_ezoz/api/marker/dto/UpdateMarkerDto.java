package ezoz.backend_ezoz.api.marker.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UpdateMarkerDto {

    @ApiModelProperty(value = "마커 위도, 예) 36.2155491", required = true)
    private String latitude;

    @ApiModelProperty(value = "마커 경도, 예) 127.487786", required = true)
    private String longitude;

    @ApiModelProperty(value = "마커 주소")
    private String address;

    @ApiModelProperty(value = "마커 제목")
    private String title;

    @ApiModelProperty(value = "마커 내용")
    private String content;

    @ApiModelProperty(value = "마커 이미지의 키값들", required = true)
    private List<String> markerImageKeys;

}
