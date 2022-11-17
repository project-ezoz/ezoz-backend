package ezoz.backend_ezoz.domain.post.controller;

import ezoz.backend_ezoz.domain.post.dto.PostDto;
import ezoz.backend_ezoz.domain.post.entity.Post;
import ezoz.backend_ezoz.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<?> save(@RequestBody PostDto.Request postRequest) {
        Long saveId = postService.save(postRequest);

        return ResponseEntity.ok(saveId);
    }

    @GetMapping("/posts/{keyword}")
    public ResponseEntity<List<PostDto.Response>> search(@PathVariable String keyword, Pageable pageable) {
        List<PostDto.Response> postResponses = postService.searchByContent(keyword);

        return ResponseEntity.ok(postResponses);
    }
 }
