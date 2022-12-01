package ezoz.backend_ezoz.domain.journal.repository.custom;

import ezoz.backend_ezoz.domain.journal.entity.Journal;

import java.util.Optional;

public interface CustomJournalRepository {
    Optional<Journal> findByIdFetchImage(Long journalId);
}
