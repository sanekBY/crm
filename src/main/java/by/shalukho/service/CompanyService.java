package by.shalukho.service;

import by.shalukho.converter.CompanyConverter;
import by.shalukho.dto.CompanyDto;
import by.shalukho.entity.CompanyEntity;
import by.shalukho.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends AbstractService<CompanyDto, CompanyEntity> {

    public CompanyService(CompanyRepository companyRepository, CompanyConverter companyConverter) {
        super(companyRepository, companyConverter, CompanyDto.class, CompanyEntity.class);
    }

    @Override
    public CompanyEntity findByActiveAndId(boolean active, Long id) {
        return ((CompanyRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<CompanyEntity> findAllByActive(boolean active) {
        return ((CompanyRepository) getRepository()).findAllByActive(active);
    }
}
