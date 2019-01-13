package by.shalukho.service;

import by.shalukho.converter.CustomerConverter;
import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.CustomerEntity;
import by.shalukho.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractUniqueNameService<CustomerDto, CustomerEntity, CustomerRepository> {

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        super(customerRepository, customerConverter);
    }

}
