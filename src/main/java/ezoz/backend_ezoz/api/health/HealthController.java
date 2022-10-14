package ezoz.backend_ezoz.api.health;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Health")
public class HealthController {

    @ApiOperation(value = "서버 상태 체크 API", tags = "Health")
    @GetMapping("/")
    public ResponseEntity<HttpStatus> healthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheckMethod(){
        return ResponseEntity.ok("okay");
    }

}
