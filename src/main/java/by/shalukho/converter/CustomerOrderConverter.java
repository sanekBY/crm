package by.shalukho.converter;

import by.shalukho.dto.CustomerOrderDto;
import by.shalukho.entity.CustomerEntity;
import by.shalukho.entity.CustomerOrderEntity;
import by.shalukho.entity.OrderItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderConverter extends GenericConverterWithEnums<CustomerOrderDto, CustomerOrderEntity> {

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private OrderItemConverter orderItemConverter;

    public CustomerOrderConverter() {
        super(CustomerOrderEntity.class);
    }

    @Override
    protected CustomerOrderEntity extraConvertToEntity(final CustomerOrderDto customerOrderDto,
                                                       final CustomerOrderEntity customerOrderEntity) {

        final CustomerOrderEntity customerOrder = super.extraConvertToEntity(customerOrderDto, customerOrderEntity);

        final CustomerEntity customer = customerConverter.convertToEntity(customerOrderDto.getCustomer());
        final List<OrderItemEntity> orderItems =
                orderItemConverter.convertAllToEntity(customerOrderDto.getOrderItems());
        orderItems.stream().forEach(i -> i.setOrder(customerOrder));

        customerOrder.setCustomerEntity(customer);
        customerOrder.setOrderItems(orderItems);

        return customerOrder;
    }

    @Override
    protected CustomerOrderDto extraConvertToDto(final CustomerOrderEntity customerOrderEntity,
                                                 final CustomerOrderDto customerOrderDto) {
        final CustomerOrderDto orderDto = super.extraConvertToDto(customerOrderEntity, customerOrderDto);
        final List<OrderItemEntity> orderItems = customerOrderEntity.getOrderItems();
        final CustomerEntity customer = customerOrderEntity.getCustomerEntity();

        orderDto.setOrderItems(orderItemConverter.convertAllToDto(orderItems));
        orderDto.setCustomer(customerConverter.convertToDto(customer));

        return orderDto;
    }
}

