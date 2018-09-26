package by.shalukho.controller;

import by.shalukho.dto.SiteSectionDto;
import by.shalukho.entity.SiteSectionEntity;
import by.shalukho.service.SiteSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/site/section")
public class SiteContentController
        extends AbstractController<SiteSectionDto, SiteSectionEntity, SiteSectionService> {

    public static final String SITE_SECTION_DTO_ATTRIBUTE = "siteSectionDto";

    @Autowired
    public SiteContentController(final SiteSectionService siteSectionService) {
        super(siteSectionService, SiteSectionDto.class);
    }

    @Override
    protected String getAttribute() {
        return SITE_SECTION_DTO_ATTRIBUTE;
    }

    @Override
    protected String getListAttribute() {
        return "sections";
    }

    @Override
    protected String getListHtml() {
        return "/site/sections";
    }

    @Override
    protected String getHtml() {
        return "/site/section";
    }
}
