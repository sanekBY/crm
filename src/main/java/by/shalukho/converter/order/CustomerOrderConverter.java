package by.shalukho.converter.order;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.entity.order.CustomerOrderEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderConverter extends GenericConverter<CustomerOrderDto, CustomerOrderEntity> {
    public CustomerOrderConverter() {
        super(CustomerOrderDto.class, CustomerOrderEntity.class);
    }
}

