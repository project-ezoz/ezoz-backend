package ezoz.backend_ezoz.infra;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    @Value("${cloud.aws.bucket}")
    private String S3Bucket;

    private final AmazonS3Client amazonS3Client;

    public String getPresignedUrl() {

        String fileName = UUID.randomUUID().toString();

        GeneratePresignedUrlRequest generatePresignedUrlRequest  =
                new GeneratePresignedUrlRequest(S3Bucket, fileName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(getExpirationDate());

        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());

        String presignedUrl = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();

        return presignedUrl;
    }

    public Date getExpirationDate() {

        Date expiration = new Date();

        long expirationTime = expiration.getTime();
        expirationTime += 1000 * 60 * 60;
        expiration.setTime(expirationTime);

        return expiration;
    }

    public void removeImage(String fileName) {
        amazonS3Client.deleteObject(S3Bucket, fileName);
    }

    public String getImageUrl(String fileName) {
        return amazonS3Client.getUrl(S3Bucket, fileName).toString();
    }

}
