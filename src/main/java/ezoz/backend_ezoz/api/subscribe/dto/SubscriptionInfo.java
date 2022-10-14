package ezoz.backend_ezoz.api.subscribe.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SubscriptionInfo {

    @NotBlank
    private String username;

    @NotBlank
    private String email;
}