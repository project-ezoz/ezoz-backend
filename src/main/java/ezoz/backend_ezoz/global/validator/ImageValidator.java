package ezoz.backend_ezoz.global.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ImageValidator {
    public boolean notExistFileName(List<MultipartFile> multipartFiles) {
        for (MultipartFile multipartFile : multipartFiles) {
            if (!StringUtils.hasText(multipartFile.getName())) {
                return true;
            }
        }

        return false;
    }
}
