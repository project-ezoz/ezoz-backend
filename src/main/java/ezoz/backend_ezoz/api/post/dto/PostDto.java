package ezoz.backend_ezoz.api.post.dto;

import ezoz.backend_ezoz.domain.post.entity.Location;
import ezoz.backend_ezoz.domain.post.entity.Post;
import ezoz.backend_ezoz.domain.post.entity.PostImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class PostDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "마커 생성 API 요청 값", description = "마커 생성에 필요한 값입니다.")
    public static class Request {

        @NotBlank
        @ApiModelProperty(value = "마커 좌표, 예) 36.2155491 127.487786", required = true)
        private String coordinate;

        @NotBlank
        @ApiModelProperty(value = "마커 주소", required = true)
        private String address;

        @NotBlank
        @ApiModelProperty(value = "마커 제목" , required = true)
        private String title;

        @NotBlank
        @ApiModelProperty(value = "마커 내용", required = true)
        private String content;

        @ApiModelProperty(value = "multipartFile 형식의 마커 이미지들", required = true)
        private List<MultipartFile> postImageFiles;

        public Post toEntity(String author, List<PostImage> postImages) {
            return Post.builder()
                    .location(new Location(coordinate, address))
                    .title(title)
                    .content(content)
                    .author(author)
                    .postImages(postImages)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "마커 검색 API 응답 값", description = "마커 검색에 따른 응답 값들입니다.")
    public static class Response {

        @ApiModelProperty(value = "마커 id", example = "1")
        private Long postId;

        @ApiModelProperty(value = "마커 좌표", example = "36.2155491 127.487786")
        private String coordinate;

        public static PostDto.Response from(Post post) {
            return Response.builder()
                    .postId(post.getPostId())
                    .coordinate(post.getLocation().getCoordinate())
                    .build();
        }
    }

}
