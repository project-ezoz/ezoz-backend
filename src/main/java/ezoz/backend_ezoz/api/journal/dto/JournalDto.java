package ezoz.backend_ezoz.api.journal.dto;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
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
        private JournalType journalType;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        private List<MultipartFile> postImageFiles;

        public Journal toEntity(String author) {
            return Journal.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
        }

    }
}
