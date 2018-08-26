package by.shalukho.converter.customer;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.customer.CompanyDto;
import by.shalukho.entity.customer.CompanyEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyConverter extends GenericConverter<CompanyDto, CompanyEntity> {

    public CompanyConverter() {
        super(CompanyDto.class, CompanyEntity.class);
    }
}
