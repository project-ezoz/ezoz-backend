package ezoz.backend_ezoz.domain.marker.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Document(indexName = "markers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Marker {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markerId;

    @Embedded
    private Location location;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<MarkerImage> markerImages = new ArrayList<>();

    @Builder
    public Marker(Location location, String title, String content, String author, List<MarkerImage> markerImages) {
        this.location = location;
        this.title = title;
        this.content = content;
        this.author = author;
        this.markerImages = markerImages;
    }
}
