package ezoz.backend_ezoz.api.subscribe;

import ezoz.backend_ezoz.api.subscribe.dto.SubscriptionInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
@Api(tags = "Subscription")
@Slf4j
public class SubscribeController {

    @PostMapping("/subscription")
    @ApiOperation(value = "구독 신청 API", notes = "이메일과 이름을 통해 구독 서비스를 신청한다.")
    public ResponseEntity<SubscriptionInfo> registerSubscription(@RequestBody SubscriptionInfo subscriptionInfo) {

        //구독자 추가 로직

        return ResponseEntity.ok(subscriptionInfo);
    }
}
