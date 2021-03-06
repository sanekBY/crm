package by.shalukho.controller;

import by.shalukho.dto.SiteReviewDto;
import by.shalukho.service.SiteReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = SiteReviewController.CURRENT_PAGE_URL)
public class SiteReviewController
        extends AbstractController<SiteReviewDto, SiteReviewService> {

    public static final String SITE_REVIEW_DTO_ATTRIBUTE = "siteReviewDto";
    public static final String CURRENT_PAGE_URL = "/site/review";

    @Autowired
    public SiteReviewController(final SiteReviewService siteReviewService) {
        super(siteReviewService, SiteReviewDto.class);
    }

    @Override
    protected String getAttribute() {
        return SITE_REVIEW_DTO_ATTRIBUTE;
    }

    @Override
    protected String getListAttribute() {
        return "reviews";
    }

    @Override
    protected String getListHtml() {
        return "site/reviews";
    }

    @Override
    protected String getHtml() {
        return "site/review";
    }

    @Override protected String getCurrentUrl() {
        return CURRENT_PAGE_URL;
    }
}
