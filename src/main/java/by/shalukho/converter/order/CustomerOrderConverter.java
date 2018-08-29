package by.shalukho.converter.order;

import by.shalukho.converter.GenericConverterWithEnums;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.entity.order.CustomerOrderEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderConverter extends GenericConverterWithEnums<CustomerOrderDto, CustomerOrderEntity> {
    public CustomerOrderConverter() {
        super(CustomerOrderEntity.class);
    }
}

