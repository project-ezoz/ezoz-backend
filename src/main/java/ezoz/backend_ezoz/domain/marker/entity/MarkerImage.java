package ezoz.backend_ezoz.domain.marker.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MarkerImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markerImageId;

    private String markerImageKey;

    @Builder
    public MarkerImage(String markerImageKey) {
        this.markerImageKey = markerImageKey;
    }
}
