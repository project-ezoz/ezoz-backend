package ezoz.backend_ezoz.api.subscribe;

import ezoz.backend_ezoz.api.subscribe.dto.SubscriptionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
@Slf4j
public class SubscribeController {

    @PostMapping("/subscription")
    public ResponseEntity<SubscriptionInfo> registerSubscription(@RequestBody SubscriptionInfo subscriptionInfo) {

        //구독자 추가 로직

        return ResponseEntity.ok(subscriptionInfo);
    }
}
