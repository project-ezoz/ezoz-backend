package ezoz.backend_ezoz.domain.post.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ezoz.backend_ezoz.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static ezoz.backend_ezoz.domain.post.entity.QPost.post;

@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> findByKeyword(String keyword) {
        return queryFactory
                .selectFrom(post)
                .where(
                        post.title.contains(keyword)
                        .or(post.content.contains(keyword))
                        .or(post.author.contains(keyword))
                )
                .fetch();
    }
}
