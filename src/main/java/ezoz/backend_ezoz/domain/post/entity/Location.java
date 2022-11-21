package ezoz.backend_ezoz.domain.post.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    private String coordinate;

    private String address;

}
