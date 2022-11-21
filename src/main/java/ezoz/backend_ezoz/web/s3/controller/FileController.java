package ezoz.backend_ezoz.web.s3.controller;

import ezoz.backend_ezoz.infra.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/upload")
    public ResponseEntity<?> upload(MultipartFile[] multipartFiles) throws IOException {
        for (MultipartFile multipartFile : multipartFiles) {
            fileService.storeFile(multipartFile);
        }

        return ResponseEntity.ok("ok");
    }
}
