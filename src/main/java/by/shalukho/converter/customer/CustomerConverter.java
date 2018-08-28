package by.shalukho.converter.customer;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.entity.customer.CustomerEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerConverter extends GenericConverter<CustomerDto, CustomerEntity> {

    public CustomerConverter() {
        super(CustomerDto.class, CustomerEntity.class);
    }
}
