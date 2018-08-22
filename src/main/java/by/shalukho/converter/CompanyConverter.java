package by.shalukho.converter;

import by.shalukho.dto.CompanyDto;
import by.shalukho.entity.CompanyEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyConverter extends GenericConverter<CompanyDto, CompanyEntity> {

    public CompanyConverter() {
        super(CompanyDto.class, CompanyEntity.class);
    }
}
