package ezoz.backend_ezoz.api.post.dto;

import ezoz.backend_ezoz.domain.post.entity.Location;
import ezoz.backend_ezoz.domain.post.entity.Post;
import ezoz.backend_ezoz.domain.postimage.entity.PostImage;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class PostDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank
        private String coordinate;

        @NotBlank
        private String address;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        @NotBlank
        private String author;

        private List<MultipartFile> postImageFiles;

        public static Post toPostEntity(String coordinate, String address, String title,
                                        String content, String author, List<PostImage> postImages) {
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
    public static class Response {

        private String coordinate;
        private String address;
        private String title;
        private String content;
        private String author;

        public static PostDto.Response from(Post post) {
            Location location = post.getLocation();
            return Response.builder()
                    .coordinate(location.getCoordinate())
                    .address(location.getAddress())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .author(post.getAuthor())
                    .build();
        }
    }

}
