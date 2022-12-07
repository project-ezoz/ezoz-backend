package ezoz.backend_ezoz.api.marker.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class UpdateMarkerDto {

    @NotNull
    private Long markerId;

    @ApiModelProperty(value = "마커 좌표, 예) 36.2155491 127.487786")
    private String coordinate;

    @ApiModelProperty(value = "마커 주소")
    private String address;

    @ApiModelProperty(value = "마커 제목")
    private String title;

    @ApiModelProperty(value = "마커 내용")
    private String content;

    @ApiModelProperty(value = "multipartFile 형식의 마커 이미지들", required = true)
    private List<MultipartFile> markerImageFiles;

}
