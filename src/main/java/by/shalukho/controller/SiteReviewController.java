package by.shalukho.controller;

import by.shalukho.dto.SiteReviewDto;
import by.shalukho.entity.SiteReviewEntity;
import by.shalukho.service.SiteReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/site/review")
public class SiteReviewController
        extends AbstractController<SiteReviewDto, SiteReviewEntity, SiteReviewService> {

    public static final String SITE_REVIEW_DTO_ATTRIBUTE = "siteReviewDto";

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
        return "/site/reviews";
    }

    @Override
    protected String getHtml() {
        return "/site/review";
    }
}
