package by.shalukho.controller;

import by.shalukho.dto.SiteStockDto;
import by.shalukho.service.SiteStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/site/stock")
public class SiteStockController
        extends AbstractController<SiteStockDto, SiteStockService> {

    public static final String SITE_STOCK_DTO_ATTRIBUTE = "siteStockDto";

    @Autowired
    public SiteStockController(final SiteStockService siteStockService) {
        super(siteStockService, SiteStockDto.class);
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
}
