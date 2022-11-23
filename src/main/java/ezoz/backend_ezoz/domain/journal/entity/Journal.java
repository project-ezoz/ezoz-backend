package ezoz.backend_ezoz.domain.journal.entity;

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
@Document(indexName = "journals")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Journal extends BaseEntity {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;

    @Column(nullable = false)
    private JournalType journalType;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "journal_id")
    private List<JournalImage> journalImages = new ArrayList<>();

    @PersistenceConstructor
    public Journal(Long journalId, JournalType journalType, String title, String content, String author) {
        this.journalId = journalId;
        journalType = journalType;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @Builder
    public Journal(JournalType journalType, String title, String content, String author, List<JournalImage> journalImages) {
        this.journalType = journalType;
        this.title = title;
        this.content = content;
        this.author = author;
        this.journalImages = journalImages;
    }
}
