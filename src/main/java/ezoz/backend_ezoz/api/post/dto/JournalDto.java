package ezoz.backend_ezoz.api.post.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class JournalDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String title;

        @NotBlank
        private String content;

        @NotBlank
        private String author;

        private List<MultipartFile> postImageFiles;
    }
}
