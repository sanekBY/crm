package by.shalukho.controller;

import by.shalukho.dto.ImageDto;
import by.shalukho.entity.ImageTypeEnum;
import by.shalukho.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = ImageUploadController.CURRENT_PAGE_URL)
public class ImageUploadController extends AbstractImageUploadController<ImageDto, ImageService> {

    public static final String CURRENT_PAGE_URL = "/site/image";

    @Autowired
    public ImageUploadController(final ImageService imageService) {
        super(imageService, ImageDto.class);
    }

    @Override
    protected List<ImageDto> getAllEntitiesForPage(final PageRequest pageRequest) {
        return getImages(pageRequest);
    }

    @Override public String handleFileUpload(final MultipartFile file, final RedirectAttributes redirectAttributes) {
        super.handleFileUpload(file, redirectAttributes);
        addSuccessAlert(redirectAttributes, getMessage("imageSaved") + " " + file.getOriginalFilename());
        return "redirect:" + getCurrentUrl() + "/list";
    }

    @Override
    protected String getAttribute() {
        return "image";
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

    @Override protected ImageTypeEnum getImageType() {
        return ImageTypeEnum.MAIN;
    }
}