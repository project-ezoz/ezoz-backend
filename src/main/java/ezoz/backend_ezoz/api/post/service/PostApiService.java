package ezoz.backend_ezoz.api.post.service;

import ezoz.backend_ezoz.api.post.dto.PostDto;
import ezoz.backend_ezoz.domain.post.entity.Post;
import ezoz.backend_ezoz.domain.post.service.PostService;
import ezoz.backend_ezoz.domain.postimage.entity.PostImage;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.jwt.TokenManager;
import ezoz.backend_ezoz.infra.FileService;
import ezoz.backend_ezoz.infra.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostApiService {

    private final PostService postService;
    private final FileService fileService;
    private final TokenManager tokenManager;

    public Long registerPost(PostDto.Request postRequestDto) {


        List<PostImage> postImages = new ArrayList<>();
        registerPostImages(postImages, postRequestDto.getPostImageFiles());

        Post post = PostDto.Request.toPostEntity(
                postRequestDto.getCoordinate(),
                postRequestDto.getAddress(),
                postRequestDto.getTitle(),
                postRequestDto.getContent(),
                "ckdgus",
                postImages
        );

        return postService.registerPost(post);
    }

    private void registerPostImages(List<PostImage> postImages, List<MultipartFile> multipartFiles) {

        if (multipartFiles == null) {
            return;
        }

        for (MultipartFile multipartFile : multipartFiles) {
            try {
                UploadFile uploadFile = fileService.storeFile(multipartFile);
                postImages.add(uploadFile.createPostImage());
            } catch (IOException e) {
                throw new BusinessException(ErrorCode.FAILED_REGISTER_POST_IMAGE);
            }
        }

    }

    public List<PostDto.Response> searchByKeyword(String keyword) {
        return postService.searchByKeyword(keyword).stream()
                .map(PostDto.Response::from)
                .collect(Collectors.toList());
    }

    public List<PostDto.Response> findByKeyword(String keyword) {
        return postService.findByKeyword(keyword).stream()
                .map(PostDto.Response::from)
                .collect(Collectors.toList());
    }
}
