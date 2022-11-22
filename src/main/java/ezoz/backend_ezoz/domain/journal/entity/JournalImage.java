package ezoz.backend_ezoz.domain.journal.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JournalImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalImageId;

    private String originalFileName; // 원본 파일 이름

    private String storeFileName; // 저장된 파일 이름

    @Builder
    public JournalImage(String originalFileName, String storeFileName) {
        this.originalFileName = originalFileName;
        this.storeFileName = storeFileName;
    }
}
