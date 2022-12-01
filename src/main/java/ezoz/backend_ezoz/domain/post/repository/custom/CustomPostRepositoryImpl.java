package ezoz.backend_ezoz.domain.post.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ezoz.backend_ezoz.domain.post.entity.Post;
import ezoz.backend_ezoz.domain.post.entity.QPost;
import ezoz.backend_ezoz.domain.post.entity.QPostImage;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static ezoz.backend_ezoz.domain.post.entity.QPost.post;
import static ezoz.backend_ezoz.domain.post.entity.QPostImage.postImage;

@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Post> findByIdFetchImage(Long postId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(post)
                .join(post.postImages, postImage).fetchJoin()
                .where(postIdEquals(postId))
                .fetchOne());
    }

    private BooleanExpression postIdEquals(Long postId) {
        return post.postId.eq(postId);
    }
}
