package by.shalukho.service.customer;

import by.shalukho.converter.customer.CompanyConverter;
import by.shalukho.dto.customer.CompanyDto;
import by.shalukho.entity.customer.CompanyEntity;
import by.shalukho.repository.CompanyRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService extends AbstractService<CompanyDto, CompanyEntity> {

    public CompanyService(CompanyRepository companyRepository, CompanyConverter companyConverter) {
        super(companyRepository, companyConverter, CompanyDto.class, CompanyEntity.class);
    }

    @Override
    public Optional<CompanyEntity> findByActiveAndId(boolean active, Long id) {
        return ((CompanyRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<CompanyEntity> findAllByActive(boolean active) {
        return ((CompanyRepository) getRepository()).findAllByActive(active);
    }
}
