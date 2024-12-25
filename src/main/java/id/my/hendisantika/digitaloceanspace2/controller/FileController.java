package id.my.hendisantika.digitaloceanspace2.controller;

import id.my.hendisantika.digitaloceanspace2.service.DigitalOceanSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-digital-ocean-space2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 25/12/24
 * Time: 08.32
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final DigitalOceanSpaceService spaceService;

    // Upload file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path tempFile = Files.createTempFile(file.getOriginalFilename(), ".tmp");
            file.transferTo(tempFile);

            String fileName = spaceService.uploadFile(tempFile);
            return new ResponseEntity<>(fileName, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
