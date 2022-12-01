package ezoz.backend_ezoz.domain.journal.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ezoz.backend_ezoz.domain.journal.entity.Journal;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static ezoz.backend_ezoz.domain.journal.entity.QJournal.*;
import static ezoz.backend_ezoz.domain.journal.entity.QJournalImage.*;

@RequiredArgsConstructor
public class CustomJournalRepositoryImpl implements CustomJournalRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Journal> findByIdFetchImage(Long journalId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(journal)
                .join(journal.journalImages, journalImage).fetchJoin()
                .where(journalIdEquals(journalId))
                .fetchOne());
    }

    private BooleanExpression journalIdEquals(Long journalId) {
        return journal.journalId.eq(journalId);
    }
}
