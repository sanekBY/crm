package by.shalukho.controller;

import by.shalukho.dto.AbstractDto;
import by.shalukho.dto.ImageDto;
import by.shalukho.entity.ImageTypeEnum;
import by.shalukho.service.AbstractService;
import by.shalukho.service.image_services.MainImageService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractImageUploadController<T extends AbstractDto, Service extends AbstractService>
        extends AbstractController<T, Service> {

    @Autowired
    private MainImageService imageService;

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
        final String path = MvcUriComponentsBuilder.fromMethodName(ImagesController.class,
                                                                   "mainImage", image.getName()).build()
                .toString();
        image.setPath(path);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @NonNull final RedirectAttributes redirectAttributes) {
        saveImage(file);
        return getHtml();
    }


    @Override
    protected void addAdditionalListAttributes(final Model model) {
        model.addAttribute("ImageUrl", getCurrentUrl() + "/");
    }

    protected Optional<ImageDto> saveImage(final MultipartFile file) {
        Optional<ImageDto> imageDto = Optional.empty();
        if (!file.isEmpty()) {
            imageDto= imageService.store(file, getImageType());
        }
        return imageDto;
    }

    protected abstract ImageTypeEnum getImageType();
}
