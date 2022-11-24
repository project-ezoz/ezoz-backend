package ezoz.backend_ezoz.domain.journal.repository.elasticsearch.custom;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;

import java.util.List;

public interface CustomJournalSearchRepository {
    List<Journal> searchByKeywordWithPaging(String keyword, JournalType journalType, int page);
}
