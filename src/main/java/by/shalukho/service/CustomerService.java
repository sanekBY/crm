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
    public Optional<CustomerEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<CustomerEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }
}
