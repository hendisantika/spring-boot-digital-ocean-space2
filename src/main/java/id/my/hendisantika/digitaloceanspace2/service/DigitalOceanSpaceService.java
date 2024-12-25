package id.my.hendisantika.digitaloceanspace2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-digital-ocean-space2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 25/12/24
 * Time: 08.19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DigitalOceanSpaceService {

    private final S3Client s3Client;

    @Value("${do.space.access-key}")
    private String accessKey;

    @Value("${do.space.secret-key}")
    private String secretKey;

    @Value("${do.space.endpoint}")
    private String endpoint;

    @Value("${do.space.bucket-name}")
    private String bucketName;

    public DigitalOceanSpaceService() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        this.s3Client = S3Client.builder()
                .region(Region.of("ap-southeast-1"))  // Use your space's region
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    // Upload file
    public String uploadFile(Path filePath) throws IOException {
        String fileName = filePath.getFileName().toString();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, filePath);
        return fileName;
    }
}
