package ezoz.backend_ezoz.api.post.controller;

import ezoz.backend_ezoz.api.post.dto.PostDto;
import ezoz.backend_ezoz.api.post.service.PostApiService;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.validator.ImageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostApiService postApiService;
    private final ImageValidator imageValidator;

    @PostMapping("/posts")
    public ResponseEntity<?> save(PostDto.Request postRequestDto, HttpServletRequest httpServletRequest) {

//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//        String accessToken = authorizationHeader.split(" ")[1];

        List<MultipartFile> postImageFiles = postRequestDto.getPostImageFiles();
        if (postImageFiles == null || imageValidator.notExistFileName(postImageFiles)) {
            throw new BusinessException(ErrorCode.NON_EXISTS_IMAGE);
        }
        Long saveId = postApiService.registerPost(postRequestDto);

        return ResponseEntity.ok(saveId);
    }


    @GetMapping("/posts/{keyword}")
    public ResponseEntity<List<PostDto.Response>> searchByES(@PathVariable String keyword) {
        List<PostDto.Response> postResponses = postApiService.searchByKeyword(keyword);

        return ResponseEntity.ok(postResponses);
    }

}
