package ezoz.backend_ezoz.domain.post.service;

import ezoz.backend_ezoz.domain.post.entity.Post;
import ezoz.backend_ezoz.domain.post.repository.PostRepository;
import ezoz.backend_ezoz.domain.post.repository.elasticsearch.PostSearchRepository;
import ezoz.backend_ezoz.global.error.exception.EntityNotFoundException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PostSearchRepository postSearchRepository;

    @Transactional
    public Long registerPost(Post post) {

        Post savedPost = postRepository.save(post);
        postSearchRepository.save(savedPost);

        return savedPost.getPostId();
    }

    public List<Post> searchByKeyword(String keyword) {
        return postSearchRepository.findByKeyword(keyword);
    }

    public Post findByIdFetchImage(Long postId) {
        return postRepository.findByIdFetchImage(postId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_VALID_TOKEN));
    }
}
