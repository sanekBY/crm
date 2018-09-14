package by.shalukho.service;

import by.shalukho.converter.CustomerConverter;
import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.CustomerEntity;
import by.shalukho.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends AbstractService<CustomerDto, CustomerEntity, CustomerRepository> {

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        super(customerRepository, customerConverter);
    }

    @Override
    public Optional<CustomerEntity> findByActiveAndId(boolean active, Long id) {
        return getRepository().findByActiveAndId(active, id);
    }

    @Override
    public List<CustomerEntity> findAllByActive(boolean active) {
        return getRepository().findAllByActive(active);
    }
}
