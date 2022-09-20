package ezoz.backend_ezoz.web.googletoken;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GoogleRequestDto {

    private String Content_Type;

    private String grant_type;

    private String client_id;

    private String redirect_uri;

    private String code;

    private String client_secret;
}
