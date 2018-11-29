package by.shalukho.controller.rest;


import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.dto.SiteReviewDto;
import by.shalukho.dto.SiteSectionDto;
import by.shalukho.dto.SiteStockDto;
import by.shalukho.service.SiteCompanyContactsService;
import by.shalukho.service.SiteReviewService;
import by.shalukho.service.SiteSectionService;
import by.shalukho.service.SiteStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SiteRestController {

    private final SiteCompanyContactsService siteCompanyContactsService;
    private final SiteReviewService siteReviewService;
    private final SiteSectionService siteSectionService;
    private final SiteStockService siteStockService;

    @Autowired
    public SiteRestController(final SiteCompanyContactsService siteCompanyContactsService,
                              final SiteReviewService siteReviewService,
                              final SiteSectionService siteSectionService,
                              final SiteStockService siteStockService) {
        this.siteCompanyContactsService = siteCompanyContactsService;
        this.siteReviewService = siteReviewService;
        this.siteSectionService = siteSectionService;
        this.siteStockService = siteStockService;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/contacts/{id}", method = RequestMethod.GET, produces = "application/json")
    public SiteCompanyContactsDto getSiteContactData(@PathVariable("id") final Long id) {
        return siteCompanyContactsService.findById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/reviews", method = RequestMethod.GET, produces = "application/json")
    public List<SiteReviewDto> getSiteReviews() {
        List<SiteReviewDto> reviews =
                siteReviewService.findAll().stream().filter(s -> s.isAccepted()).collect(Collectors.toList());
        return reviews;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/review", method = RequestMethod.POST)
    public void saveReview(final SiteReviewDto siteReviewDto) {
        siteReviewService.save(siteReviewDto);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/sections", method = RequestMethod.GET, produces = "application/json")
    public List<SiteSectionDto> getSiteSections() {
        final List<SiteSectionDto> sections = siteSectionService.findAllSimpleSection();
        return sections;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/stocks", method = RequestMethod.GET, produces = "application/json")
    public List<SiteStockDto> getSiteStocks() {
        final List<SiteStockDto> stocks = siteStockService.findAll();
        return stocks;
    }

}


