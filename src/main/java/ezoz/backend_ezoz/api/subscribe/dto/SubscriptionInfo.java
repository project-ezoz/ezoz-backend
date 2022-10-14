package ezoz.backend_ezoz.api.subscribe.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
public class SubscriptionInfo {

    @NotBlank
    @ApiModelProperty(value = "사용자 이름", required = true, example = "김요한")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "사용자 이메일", required = true, example = "rladygks@gmail.com")
    private String email;
}
