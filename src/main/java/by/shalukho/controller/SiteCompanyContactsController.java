package by.shalukho.controller;

import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.entity.SiteCompanyContactsEntity;
import by.shalukho.service.SiteCompanyContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/site/contact")
public class SiteCompanyContactsController
        extends AbstractController<SiteCompanyContactsDto, SiteCompanyContactsEntity> {

    @Autowired
    public SiteCompanyContactsController(final SiteCompanyContactsService siteCompanyContactsService) {
        super(siteCompanyContactsService, SiteCompanyContactsDto.class);
    }

    @Override
    protected String getAttribute() {
        return "siteCompanyContactsDto";
    }

    @Override
    protected String getListAttribute() {
        return "contacts";
    }

    @Override
    protected String getListHtml() {
        return "/site/contacts";
    }

    @Override
    protected String getHtml() {
        return "/site/contact";
    }
}
