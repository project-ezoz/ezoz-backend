package ezoz.backend_ezoz.api.post.controller;

import ezoz.backend_ezoz.api.post.dto.PostDto;
import ezoz.backend_ezoz.api.post.service.PostApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostApiService postApiService;

    @PostMapping("/posts")
    public ResponseEntity<?> save(PostDto.Request postRequest, HttpServletRequest httpServletRequest){

//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//        String accessToken = authorizationHeader.split(" ")[1];
        Long saveId = postApiService.registerPost(postRequest);

        return ResponseEntity.ok(saveId);
    }

    @GetMapping("/posts/{keyword}")
    public ResponseEntity<List<PostDto.Response>> searchByES(@PathVariable String keyword) {
        List<PostDto.Response> postResponses = postApiService.searchByKeyword(keyword);

        return ResponseEntity.ok(postResponses);
    }

}
