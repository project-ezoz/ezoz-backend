package ezoz.backend_ezoz.domain.marker.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    private Double latitude;

    private Double longitude;

    private String address;

    public void updateLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void updateLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void updateAddress(String address) {
        this.address = address;
    }
}
