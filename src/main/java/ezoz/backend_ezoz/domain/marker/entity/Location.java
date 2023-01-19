package ezoz.backend_ezoz.domain.marker.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    private String latitude;

    private String longitude;

    private String address;

    public void updateLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void updateLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void updateAddress(String address) {
        this.address = address;
    }
}
