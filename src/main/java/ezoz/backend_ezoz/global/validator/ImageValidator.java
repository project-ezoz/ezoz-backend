package ezoz.backend_ezoz.global.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        return false;
    }

    public boolean duplicateImageName(List<MultipartFile> multipartFiles) {

        Set<String> fileNameSet = new HashSet<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!fileNameSet.contains(multipartFile.getOriginalFilename())) {
                fileNameSet.add(multipartFile.getOriginalFilename());
            } else return true;
        }

        return false;
    }

}
