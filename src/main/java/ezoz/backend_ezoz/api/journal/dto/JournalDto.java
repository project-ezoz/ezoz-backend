package ezoz.backend_ezoz.api.journal.dto;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.entity.JournalImage;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;
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
        private String journalType;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        private List<MultipartFile> journalImageFiles;

        public Journal toEntity(String author, List<JournalImage> journalImages) {
            return Journal.builder()
                    .journalType(JournalType.from(journalType))
                    .title(title)
                    .content(content)
                    .author(author)
                    .journalImages(journalImages)
                    .build();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private String title;

        private String JournalImageUrl;

        public static JournalDto.Response of(String title, String journalImageUrl) {
            return Response.builder()
                    .title(title)
                    .JournalImageUrl(journalImageUrl)
                    .build();
        }

    }
}
