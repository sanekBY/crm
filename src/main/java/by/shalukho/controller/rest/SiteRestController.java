package by.shalukho.controller.rest;


import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.dto.SiteReviewDto;
import by.shalukho.service.SiteCompanyContactsService;
import by.shalukho.service.SiteReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public SiteRestController(final SiteCompanyContactsService siteCompanyContactsService,
                              final SiteReviewService siteReviewService) {
        this.siteCompanyContactsService = siteCompanyContactsService;
        this.siteReviewService = siteReviewService;
    }

    @RequestMapping(value = "/api/contacts/{id}", method = RequestMethod.GET, produces = "application/json")
    public SiteCompanyContactsDto getSiteContactData(@PathVariable("id") final Long id) {
        return siteCompanyContactsService.findById(id);
    }

    @RequestMapping(value = "/api/reviews", method = RequestMethod.GET, produces = "application/json")
    public List<SiteReviewDto> getSiteReviews() {
        List<SiteReviewDto> reviews =
                siteReviewService.findAll().stream().filter(s -> s.isAccepted()).collect(Collectors.toList());
        return reviews;
    }

    @RequestMapping(value = "/api/review", method = RequestMethod.POST)
    public void saveReview(final SiteReviewDto siteReviewDto) {
        siteReviewService.save(siteReviewDto);
    }

}


