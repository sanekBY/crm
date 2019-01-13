package by.shalukho.controller;


import by.shalukho.service.image_services.MainImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImagesController {

    private final MainImageService imageService;

    @Autowired
    public ImagesController(final MainImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> mainImage(@PathVariable String filename) {
        final Resource file = imageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                          "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}


