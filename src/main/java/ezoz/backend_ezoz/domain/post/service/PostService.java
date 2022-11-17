package ezoz.backend_ezoz.domain.post.service;

import ezoz.backend_ezoz.domain.post.dto.PostDto;
import ezoz.backend_ezoz.domain.post.entity.Post;
import ezoz.backend_ezoz.domain.post.repository.PostRepository;
import ezoz.backend_ezoz.domain.post.repository.elasticsearch.PostSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PostSearchRepository postSearchRepository;

    @Transactional
    public Long save(PostDto.Request postRequestDto) {
        Post post = PostDto.Request.toPostEntity(postRequestDto.getTitle(),
                postRequestDto.getContent(),
                postRequestDto.getAuthor());
        Post savedPost = postRepository.save(post);
        postSearchRepository.save(savedPost);

        return savedPost.getPostId();
    }

    public List<PostDto.Response> searchByContent(String keyword) {
        return postSearchRepository.findByContentContains(keyword)
                .stream()
                .map(PostDto.Response::from)
                .collect(Collectors.toList());
    }
}
