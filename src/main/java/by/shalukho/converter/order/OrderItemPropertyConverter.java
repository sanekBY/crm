package by.shalukho.converter.order;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.order.OrderItemPropertyDto;
import by.shalukho.entity.order.OrderItemPropertyEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemPropertyConverter extends GenericConverter<OrderItemPropertyDto, OrderItemPropertyEntity> {
    public OrderItemPropertyConverter() {
        super(OrderItemPropertyEntity.class);
    }
}
