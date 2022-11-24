package ezoz.backend_ezoz.domain.journal.service;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;
import ezoz.backend_ezoz.domain.journal.repository.JournalRepository;
import ezoz.backend_ezoz.domain.journal.repository.elasticsearch.JournalSearchRepository;
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

    public Long save(Journal journal) {
        Journal savedId = journalRepository.save(journal);
        journalSearchRepository.save(journal);

        return savedId.getJournalId();
    }
    public List<Journal> searchByKeywordWithPaging(String keyword, JournalType journalType, int page) {
        return journalSearchRepository.searchByKeywordWithPaging(keyword, journalType, page);
    }
}
