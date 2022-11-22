package ezoz.backend_ezoz.domain.post.entity;

import ezoz.backend_ezoz.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Document(indexName = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

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
    private List<PostImage> postImages = new ArrayList<>();

    @Builder
    public Post(Location location, String title, String content, String author, List<PostImage> postImages) {
        this.location = location;
        this.title = title;
        this.content = content;
        this.author = author;
        this.postImages = postImages;
    }

    @PersistenceConstructor
    public Post(Long postId, Location location, String title, String content, String author) {
        this.postId = postId;
        this.location = location;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
