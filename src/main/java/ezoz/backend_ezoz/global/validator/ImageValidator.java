package ezoz.backend_ezoz.global.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ImageValidator {
    public boolean notExistFileName(List<MultipartFile> multipartFiles) {

        if (multipartFiles == null) {
            return true;
        }

        for (MultipartFile multipartFile : multipartFiles) {
            if (!StringUtils.hasText(multipartFile.getOriginalFilename())) {
                return true;
            }
        }

        // TODO: 2022/12/03 똑같은 이름을 가진 이미지가 있을 수 없게 검증 로직 작성
        return false;
    }
}
