package ezoz.backend_ezoz.global.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ImageValidator {

    public boolean notExistFileName(List<String> markerImageKeys) {

        if (markerImageKeys == null) {
            return true;
        }

        if (markerImageKeys.size() == 0) {
            return true;
        }

        for (String markerImageKey : markerImageKeys) {
            if (!StringUtils.hasText(markerImageKey)) {
                return true;
            }
        }

        return false;
    }

    public boolean duplicateImageName(List<String> markerImageKeys) {

        Set<String> fileNameSet = new HashSet<>();

        for (String markerImageKey : markerImageKeys) {
            if (!fileNameSet.contains(markerImageKey)) {
                fileNameSet.add(markerImageKey);
            } else return true;
        }

        return false;
    }

}
