package ezoz.backend_ezoz.domain.journal.repository;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.repository.custom.CustomJournalRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long>, CustomJournalRepository {
}
