package ezoz.backend_ezoz.api.subscribe;

import ezoz.backend_ezoz.api.subscribe.dto.SubscriptionInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class SubscribeController {

    @PostMapping("/subscription")
    public ResponseEntity<SubscriptionInfo> registerSubscription(SubscriptionInfo subscriptionInfo) {
        return ResponseEntity.ok(subscriptionInfo);
    }
}
