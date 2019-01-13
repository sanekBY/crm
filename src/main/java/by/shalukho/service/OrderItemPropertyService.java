package by.shalukho.service;

import by.shalukho.converter.OrderItemPropertyConverter;
import by.shalukho.dto.OrderItemPropertyDto;
import by.shalukho.entity.OrderItemPropertyEntity;
import by.shalukho.repository.OrderItemPropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemPropertyService
        extends AbstractService<OrderItemPropertyDto, OrderItemPropertyEntity, OrderItemPropertyRepository> {

    public OrderItemPropertyService(OrderItemPropertyRepository orderItemPropertyRepository,
                                    OrderItemPropertyConverter orderItemPropertyConverter) {
        super(orderItemPropertyRepository, orderItemPropertyConverter);
    }
}
