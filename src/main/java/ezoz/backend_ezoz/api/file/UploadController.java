package ezoz.backend_ezoz.api.file;

import ezoz.backend_ezoz.infra.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Upload")
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final FileService fileService;

    @GetMapping("/upload")
    @ApiOperation(value = "presigend url 조회 API", notes = "파일 업로드를 위해 presignedUrl을 제공한다.")
    public ResponseEntity<?> getPresignedUrl() {
        return ResponseEntity.ok(fileService.getPresignedUrl());
    }
}
