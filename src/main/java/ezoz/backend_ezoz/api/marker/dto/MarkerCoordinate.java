package ezoz.backend_ezoz.api.marker.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MarkerCoordinate {

    private Coordinate ne;

    private Coordinate nw;

    private Coordinate se;

    private Coordinate sw;

    @Getter
    public static class Coordinate {

        @NotBlank
        private Double lat;

        @NotBlank
        private Double lng;
    }

}
