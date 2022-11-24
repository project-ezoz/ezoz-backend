package ezoz.backend_ezoz.infra;

import ezoz.backend_ezoz.domain.journal.entity.JournalImage;
import ezoz.backend_ezoz.domain.post.entity.PostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFile {

    private String originalFileName; // 원본 파일 이름
    private String storeFileName; // 저장된 파일 이름

    // TODO: 2022. 11. 24. 나중에 하나의 이미지로 합치기
    public PostImage createPostImage() {
        return PostImage.builder()
                .originalFileName(originalFileName)
                .storeFileName(storeFileName)
                .build();
    }

    public JournalImage createJournalImage() {
        return JournalImage.builder()
                .originalFileName(originalFileName)
                .storeFileName(storeFileName)
                .build();
    }

}
