package ezoz.backend_ezoz.api.subscribe.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SubscriptionInfo {

    @NotBlank
    private String username;

    @NotBlank
    private String email;
}
