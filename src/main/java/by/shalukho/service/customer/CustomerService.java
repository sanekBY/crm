package by.shalukho.service.customer;

import by.shalukho.converter.customer.CustomerConverter;
import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.entity.customer.CustomerEntity;
import by.shalukho.repository.CustomerRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends AbstractService<CustomerDto, CustomerEntity> {

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        super(customerRepository, customerConverter);
    }

    @Override
    public Optional<CustomerEntity> findByActiveAndId(boolean active, Long id) {
        return ((CustomerRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<CustomerEntity> findAllByActive(boolean active) {
        return ((CustomerRepository) getRepository()).findAllByActive(active);
    }
}
