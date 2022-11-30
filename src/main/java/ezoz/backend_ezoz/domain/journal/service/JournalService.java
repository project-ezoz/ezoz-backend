package ezoz.backend_ezoz.domain.journal.service;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;
import ezoz.backend_ezoz.domain.journal.repository.JournalRepository;
import ezoz.backend_ezoz.domain.journal.repository.elasticsearch.JournalSearchRepository;
import ezoz.backend_ezoz.global.error.exception.EntityNotFoundException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;
    private final JournalSearchRepository journalSearchRepository;

    @Transactional
    public Long save(Journal journal) {
        Journal savedId = journalRepository.save(journal);
        journalSearchRepository.save(journal);

        return savedId.getJournalId();
    }

    public List<Journal> searchByKeywordWithPaging(String keyword, JournalType journalType, int offset) {
        return journalSearchRepository.searchByKeywordWithPaging(keyword, journalType, offset);
    }

    public Journal findById(Long journalId) {
        return journalRepository.findById(journalId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.JOURNAL_NOT_EXISTS));
    }
}
