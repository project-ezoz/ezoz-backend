package ezoz.backend_ezoz.api.post.controller;

import ezoz.backend_ezoz.api.post.dto.PostDetailDto;
import ezoz.backend_ezoz.api.post.dto.PostDto;
import ezoz.backend_ezoz.api.post.service.PostApiService;
import ezoz.backend_ezoz.global.error.exception.BusinessException;
import ezoz.backend_ezoz.global.error.exception.ErrorCode;
import ezoz.backend_ezoz.global.validator.ImageValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Api(tags = "Post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostApiService postApiService;
    private final ImageValidator imageValidator;

    @PostMapping(value = "/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "마커 생성 API", notes = "마커를 새로 등록한다.")
    public ResponseEntity<?> registerPost(PostDto.Request postRequestDto, HttpServletRequest httpServletRequest) {

//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//        String accessToken = authorizationHeader.split(" ")[1];

        List<MultipartFile> postImageFiles = postRequestDto.getPostImageFiles();
        if (postImageFiles == null || imageValidator.notExistFileName(postImageFiles)) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_EXISTS);
        }
        Long saveId = postApiService.registerPost(postRequestDto);

        return ResponseEntity.ok(saveId);
    }

    @GetMapping("/posts")
    @ApiOperation(value = "마커 검색 API", notes = "키워드에 따라 추출된 마커를 제공한다.")
    public ResponseEntity<List<PostDto.Response>> searchByES(@RequestParam String keyword) {
        List<PostDto.Response> postResponses = postApiService.searchByKeyword(keyword);

        return ResponseEntity.ok(postResponses);
    }

    @GetMapping("/posts/{postId}")
    @ApiOperation(value = "마커 조회 API", notes = "마커 id를 통해 해당하는 마커를 제공한다.")
    public ResponseEntity<PostDetailDto> getPostDetail(@PathVariable Long postId) {

        PostDetailDto postDetail = postApiService.getPostDetail(postId);

        return ResponseEntity.ok(postDetail);
    }
}
