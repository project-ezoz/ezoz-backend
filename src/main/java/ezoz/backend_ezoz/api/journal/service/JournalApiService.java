package ezoz.backend_ezoz.api.journal.service;

import ezoz.backend_ezoz.api.journal.dto.JournalDto;
import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.service.JournalService;
import ezoz.backend_ezoz.global.jwt.TokenManager;
import ezoz.backend_ezoz.infra.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JournalApiService {

    private final JournalService journalService;
    private final FileService fileService;
    private final TokenManager tokenManager;

    public Long registerJournal(JournalDto.Request journalRequestDto) {
        Journal journal = journalRequestDto.toEntity("ckdgus");

        return journalService.save(journal);
    }


}
