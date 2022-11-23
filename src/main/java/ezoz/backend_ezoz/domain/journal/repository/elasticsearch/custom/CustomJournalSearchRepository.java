package ezoz.backend_ezoz.domain.journal.repository.elasticsearch.custom;

import ezoz.backend_ezoz.domain.journal.entity.Journal;

import java.util.List;

public interface CustomJournalSearchRepository {
    List<Journal> searchByKeyword(String keyword);
}
