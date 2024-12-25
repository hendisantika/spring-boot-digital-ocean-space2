package id.my.hendisantika.digitaloceanspace2.controller;

import id.my.hendisantika.digitaloceanspace2.service.DigitalOceanSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
