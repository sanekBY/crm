package by.shalukho.service;

import by.shalukho.converter.CustomerConverter;
import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.CustomerEntity;
import by.shalukho.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends AbstractUniqueNameService<CustomerDto, CustomerEntity, CustomerRepository> {

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

    @Override
    public Page<CustomerEntity> findAllByActiveIsTrue(final Pageable pageable) {
        return getRepository().findAllByActiveIsTrue(pageable);
    }

    @Override
    public Optional<CustomerEntity> findByName(final String name) {
        return getRepository().findByName(name);
    }
}
