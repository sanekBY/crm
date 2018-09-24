package by.shalukho.controller.rest;


import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.service.SiteCompanyContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteRestController {

    private final SiteCompanyContactsService siteCompanyContactsService;

    @Autowired
    public SiteRestController(final SiteCompanyContactsService siteCompanyContactsService) {
        this.siteCompanyContactsService = siteCompanyContactsService;
    }

    @RequestMapping(value = "/api/contacts/{id}", method = RequestMethod.GET, produces = "application/json")
    public SiteCompanyContactsDto getSiteContactData(@PathVariable("id") final Long id) {
        return siteCompanyContactsService.findById(id);
    }

}


