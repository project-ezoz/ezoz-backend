package ezoz.backend_ezoz.domain.journal.repository.elasticsearch;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.repository.elasticsearch.custom.CustomJournalSearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface JournalSearchRepository extends ElasticsearchRepository<Journal, Long>, CustomJournalSearchRepository {
}
