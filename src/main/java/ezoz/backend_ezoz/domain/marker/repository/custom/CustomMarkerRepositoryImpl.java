package ezoz.backend_ezoz.domain.marker.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ezoz.backend_ezoz.domain.marker.entity.Marker;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static ezoz.backend_ezoz.domain.marker.entity.QMarker.marker;
import static ezoz.backend_ezoz.domain.marker.entity.QMarkerImage.markerImage;

@RequiredArgsConstructor
public class CustomMarkerRepositoryImpl implements CustomMarkerRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Marker> findByIdFetchImage(Long postId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(marker)
                .join(marker.markerImages, markerImage).fetchJoin()
                .where(markerIdEquals(postId))
                .fetchOne());
    }

    private BooleanExpression markerIdEquals(Long maerkerId) {
        return marker.markerId.eq(maerkerId);
    }
}
