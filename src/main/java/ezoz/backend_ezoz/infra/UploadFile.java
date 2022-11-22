package ezoz.backend_ezoz.infra;

import ezoz.backend_ezoz.domain.post.entity.PostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFile {

    private String originalFileName; // 원본 파일 이름
    private String storeFileName; // 저장된 파일 이름

    public PostImage createPostImage() {
        return PostImage.builder()
                .originalFileName(originalFileName)
                .storeFileName(storeFileName)
                .build();
    }

}
