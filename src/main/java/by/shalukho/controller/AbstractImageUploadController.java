package by.shalukho.controller;

import by.shalukho.dto.AbstractDto;
import by.shalukho.dto.ImageDto;
import by.shalukho.entity.ImageTypeEnum;
import by.shalukho.service.AbstractService;
import by.shalukho.service.ImageService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractImageUploadController<T extends AbstractDto, Service extends AbstractService>
        extends AbstractController<T, Service> {

    @Autowired
    private ImageService imageService;

    public AbstractImageUploadController(final Service service, final Class<T> clazz) {
        super(service, clazz);
    }

    protected List<ImageDto> getImages(@NonNull final PageRequest pageRequest) {
        final List<ImageDto> allEntitiesForPage = imageService.findAllByActiveIsTrue(pageRequest, getImageType());
        final List<ImageDto> serveFile = allEntitiesForPage.stream().peek(
                image -> prepareImagePath(image)).collect(Collectors.toList());
        return serveFile;
    }

    protected void prepareImagePath(final ImageDto image) {
        final String path = MvcUriComponentsBuilder.fromMethodName(AbstractImageUploadController.class,
                                                                   "serveFile", image.getName()).build()
                .toString();
        image.setPath(path);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        final Resource file = imageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                          "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @NonNull final RedirectAttributes redirectAttributes) {
        imageService.store(file, getImageType());
        return getHtml();
    }

    protected abstract ImageTypeEnum getImageType();
}
