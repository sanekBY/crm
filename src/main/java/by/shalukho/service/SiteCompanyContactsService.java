package by.shalukho.service;

import by.shalukho.converter.SiteCompanyContactsConverter;
import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.entity.SiteCompanyContactsEntity;
import by.shalukho.repository.SiteCompanyContactsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Optional<SiteCompanyContactsEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<SiteCompanyContactsEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }

    @Override
    public Page<SiteCompanyContactsEntity> findAllByActiveIsTrue(final Pageable pageable) {
        return getRepository().findAllByActiveIsTrue(pageable);
    }
}
