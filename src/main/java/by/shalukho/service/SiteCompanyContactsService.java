package by.shalukho.service;

import by.shalukho.converter.SiteCompanyContactsConverter;
import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.entity.SiteCompanyContactsEntity;
import by.shalukho.repository.SiteCompanyContactsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteCompanyContactsService
        extends AbstractService<SiteCompanyContactsDto, SiteCompanyContactsEntity, SiteCompanyContactsRepository> {

    public SiteCompanyContactsService(SiteCompanyContactsRepository siteCompanyContactsRepository,
                                      SiteCompanyContactsConverter siteCompanyContactsConverter) {
        super(siteCompanyContactsRepository, siteCompanyContactsConverter);
    }

    @Override
    public Optional<SiteCompanyContactsEntity> findByActiveAndId(boolean active, Long id) {
        return getRepository().findByActiveAndId(active, id);
    }

    @Override
    public List<SiteCompanyContactsEntity> findAllByActive(boolean active) {
        return getRepository().findAllByActive(active);
    }
}
