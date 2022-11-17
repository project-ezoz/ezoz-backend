package ezoz.backend_ezoz.domain.post.dto;

import ezoz.backend_ezoz.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String title;
        private String content;
        private String author;

        public static Post toPostEntity(String title, String content, String author) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String title;
        private String content;
        private String author;

        public static PostDto.Response from(Post post) {
            return Response.builder()
                    .title(post.getTitle())
                    .content(post.getContent())
                    .author(post.getAuthor())
                    .build();
        }
    }

}
