package ezoz.backend_ezoz.domain.journal.repository.elasticsearch.custom;

import ezoz.backend_ezoz.domain.journal.entity.Journal;
import ezoz.backend_ezoz.domain.journal.entity.JournalType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomJournalSearchRepositoryImpl implements CustomJournalSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Journal> searchByKeywordWithPaging(String keyword, JournalType journalType, int page) {
        Criteria criteria = Criteria.where("title").contains(keyword)
                .or(Criteria.where("content").contains(keyword))
                .or(Criteria.where("author").contains(keyword))
                .and(Criteria.where("journalType").is(journalType.toString()));

        SearchHits<Journal> search = elasticsearchOperations
                .search(new CriteriaQuery(criteria, PageRequest.of(page, 6)),
                Journal.class);

        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
