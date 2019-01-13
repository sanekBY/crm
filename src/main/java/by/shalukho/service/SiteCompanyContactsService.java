package by.shalukho.service;

import by.shalukho.converter.SiteCompanyContactsConverter;
import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.entity.SiteCompanyContactsEntity;
import by.shalukho.repository.SiteCompanyContactsRepository;
import org.springframework.stereotype.Service;

@Service
public class SiteCompanyContactsService
        extends AbstractService<SiteCompanyContactsDto, SiteCompanyContactsEntity, SiteCompanyContactsRepository> {

    public SiteCompanyContactsService(SiteCompanyContactsRepository siteCompanyContactsRepository,
                                      SiteCompanyContactsConverter siteCompanyContactsConverter) {
        super(siteCompanyContactsRepository, siteCompanyContactsConverter);
    }
}
