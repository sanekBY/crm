package by.shalukho.controller;

import by.shalukho.dto.ImageDto;
import by.shalukho.dto.SiteStockDto;
import by.shalukho.entity.ImageTypeEnum;
import by.shalukho.service.SiteStockService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = SiteStockController.CURRENT_PAGE_URL)
public class SiteStockController
        extends AbstractImageUploadController<SiteStockDto, SiteStockService> {

    public static final String SITE_STOCK_DTO_ATTRIBUTE = "siteStockDto";
    public static final String CURRENT_PAGE_URL = "/site/stock";

    @Autowired
    public SiteStockController(final SiteStockService siteStockService) {
        super(siteStockService, SiteStockDto.class);
    }

    @Override
    protected SiteStockDto getEntity(final Long id) {
        final SiteStockDto stock = super.getEntity(id);
        if (stock.getImage() != null) {
            prepareImagePath(stock.getImage());
        }
        return stock;
    }


    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String createEntityWithFile(@ModelAttribute final SiteStockDto dto,
                                       @NonNull final RedirectAttributes redirectAttributes,
                                       @RequestParam("file") MultipartFile file) {
        final Optional<ImageDto> imageDto = saveImage(file);
        if (imageDto.isPresent()) {
            dto.setImage(imageDto.get());
        }
        return createEntity(dto, redirectAttributes, getService());
    }

    @Override
    protected String getAttribute() {
        return SITE_STOCK_DTO_ATTRIBUTE;
    }

    @Override
    protected String getListAttribute() {
        return "stocks";
    }

    @Override
    protected String getListHtml() {
        return "site/stocks";
    }

    @Override
    protected String getHtml() {
        return "site/stock";
    }

    @Override
    protected String getCurrentUrl() {
        return CURRENT_PAGE_URL;
    }

    @Override
    protected ImageTypeEnum getImageType() {
        return ImageTypeEnum.NEWS;
    }
}
