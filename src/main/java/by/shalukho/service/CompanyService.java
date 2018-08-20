package by.shalukho.service;

import by.shalukho.converter.CompanyConverter;
import by.shalukho.dto.CompanyDto;
import by.shalukho.entity.CompanyEntity;
import by.shalukho.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends AbstractService<CompanyDto, CompanyEntity> {

    public CompanyService(CompanyRepository companyRepository, CompanyConverter companyConverter) {
        super(companyRepository, companyConverter, CompanyDto.class, CompanyEntity.class);
    }

}
