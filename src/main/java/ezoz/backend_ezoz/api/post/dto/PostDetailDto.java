package ezoz.backend_ezoz.api.post.dto;

import ezoz.backend_ezoz.domain.post.entity.Post;
import lombok.Builder;

import java.util.List;

@Builder
public class PostDetailDto {

    private Long postId;

    private String coordinate;

    private String address;

    private String content;

    private String author;

    private List<String> postImageUrls;

    public static PostDetailDto of(Post post, List<String> postImageUrls) {
        return PostDetailDto.builder()
                .postId(post.getPostId())
                .coordinate(post.getLocation().getCoordinate())
                .address(post.getLocation().getAddress())
                .content(post.getContent())
                .author(post.getAuthor())
                .postImageUrls(postImageUrls)
                .build();
    }
}
