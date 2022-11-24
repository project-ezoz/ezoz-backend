package ezoz.backend_ezoz.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${cloud.aws.bucket}")
    private String S3Bucket;

    private final AmazonS3Client amazonS3Client;

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = UUID.randomUUID().toString();
        ObjectMetadata objectMetadata =
                createObjectMetadata(multipartFile.getContentType(), multipartFile.getSize());

        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, storeFileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );


        return new UploadFile(originalFilename, storeFileName);

    }

    private ObjectMetadata createObjectMetadata(String contentType, long contentLength) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(contentLength);

        return objectMetadata;
    }

    public String getImageUrl(String fileName) {
        return amazonS3Client.getUrl(S3Bucket, fileName).toString();
    }


}
