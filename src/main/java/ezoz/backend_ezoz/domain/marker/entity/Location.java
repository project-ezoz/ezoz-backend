package ezoz.backend_ezoz.domain.marker.entity;

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

    public void updateCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public void updateAddress(String address) {
        this.address = address;
    }
}
