package by.shalukho.converter;

import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.entity.SiteCompanyContactsEntity;
import org.springframework.stereotype.Service;

@Service
public class SiteCompanyContactsConverter extends GenericConverter<SiteCompanyContactsDto, SiteCompanyContactsEntity> {

    public SiteCompanyContactsConverter() {
        super(SiteCompanyContactsEntity.class);
    }

}
