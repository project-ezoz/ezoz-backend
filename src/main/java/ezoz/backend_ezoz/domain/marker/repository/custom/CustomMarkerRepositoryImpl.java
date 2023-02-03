package ezoz.backend_ezoz.domain.marker.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ezoz.backend_ezoz.api.marker.dto.MarkerCoordinate;
import ezoz.backend_ezoz.domain.marker.entity.Marker;
import lombok.RequiredArgsConstructor;

import java.util.List;
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

    @Override
    public List<Marker> findByCoordinate(MarkerCoordinate markerCoordinate) {
        return queryFactory
                .selectFrom(marker)
                .where(marker.location.latitude
                        .between(markerCoordinate.getSw().getLat(), markerCoordinate.getNw().getLat())
                        .and(marker.location.longitude
                                .between(markerCoordinate.getNw().getLng(), markerCoordinate.getNe().getLng())))
                .fetch();
    }


    private BooleanExpression markerIdEquals(Long maerkerId) {
        return marker.markerId.eq(maerkerId);
    }
}
