package ezoz.backend_ezoz.api.marker.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class UpdateMarkerDto {

    @NotBlank
    private Long markerId;

    @ApiModelProperty(value = "마커 좌표, 예) 36.2155491 127.487786")
    private String coordinate;

    @ApiModelProperty(value = "마커 주소")
    private String address;

    @ApiModelProperty(value = "마커 제목")
    private String title;

    @ApiModelProperty(value = "마커 내용")
    private String content;

    private List<MultipartFile> markerImageFiles;

}
