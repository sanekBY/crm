package by.shalukho.controller;

import by.shalukho.dto.ImageDto;
import by.shalukho.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = ImageUploadController.CURRENT_PAGE_URL)
public class ImageUploadController extends AbstractController<ImageDto, ImageService> {

    public static final String CURRENT_PAGE_URL = "/site/image";

    @Autowired
    public ImageUploadController(final ImageService imageService) {
        super(imageService, ImageDto.class);
    }

    @Override
    protected List<ImageDto> getAllEntitiesForPage(final PageRequest pageRequest) {
        final List<ImageDto> allEntitiesForPage = super.getAllEntitiesForPage(pageRequest);
        final List<ImageDto> serveFile = allEntitiesForPage.stream().peek(
                image -> {
                    String path = MvcUriComponentsBuilder.fromMethodName(ImageUploadController.class,
                                                                         "serveFile", image.getName()).build()
                            .toString();
                    image.setPath(path);
                }).collect(Collectors.toList());
        return serveFile;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        final Resource file = getService().loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                          "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        getService().store(file);
        addSuccessAlert(redirectAttributes, getMessage("imageSaved") + " " + file.getOriginalFilename());
        return "redirect:" + CURRENT_PAGE_URL + "/list";
    }


    @Override
    protected String getAttribute() {
        return "imageDto";
    }

    @Override
    protected String getListAttribute() {
        return "images";
    }

    @Override
    protected String getListHtml() {
        return "image/images";
    }

    @Override
    protected String getHtml() {
        return "image/image";
    }

    @Override
    protected String getCurrentUrl() {
        return CURRENT_PAGE_URL;
    }
}