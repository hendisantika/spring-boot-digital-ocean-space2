package id.my.hendisantika.digitaloceanspace2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
}
