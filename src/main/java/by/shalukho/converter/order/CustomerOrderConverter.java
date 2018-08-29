package by.shalukho.converter.order;

import by.shalukho.converter.GenericConverterWithEnums;
import by.shalukho.converter.customer.CustomerConverter;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.entity.order.CustomerOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderConverter extends GenericConverterWithEnums<CustomerOrderDto, CustomerOrderEntity> {

    @Autowired
    private CustomerConverter customerConverter;

    public CustomerOrderConverter() {
        super(CustomerOrderEntity.class);
    }

    @Override
    protected CustomerOrderEntity extraConvertToEntity(final CustomerOrderDto customerOrderDto,
                                                       final CustomerOrderEntity customerOrderEntity) {

        CustomerOrderEntity entity = super.extraConvertToEntity(customerOrderDto, customerOrderEntity);
        entity.setCustomerEntity(customerConverter.convertToEntity(customerOrderDto.getCustomer()));

        return entity;
    }
}

