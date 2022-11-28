package ezoz.backend_ezoz.api.journal.dto;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import lombok.Builder;

import java.util.List;

@Builder
public class JournalDetailDto {

    private Long journalId;

    private String title;

    private String content;

    private String author;

    private List<String> journalImageUrls;

    public static JournalDetailDto of(Journal journal, List<String> journalImageUrls) {

        return JournalDetailDto.builder()
                .journalId(journal.getJournalId())
                .title(journal.getTitle())
                .content(journal.getContent())
                .author(journal.getAuthor())
                .journalImageUrls(journalImageUrls)
                .build();
    }



}
