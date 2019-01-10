package by.shalukho.controller;

import by.shalukho.dto.SiteStockDto;
import by.shalukho.entity.ImageTypeEnum;
import by.shalukho.service.SiteStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        final SiteStockDto entity = super.getEntity(id);
        prepareImagePath(entity.getImage());
        return entity;
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
