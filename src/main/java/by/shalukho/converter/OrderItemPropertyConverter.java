package by.shalukho.converter;

import by.shalukho.dto.OrderItemPropertyDto;
import by.shalukho.entity.OrderItemPropertyEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemPropertyConverter extends GenericConverter<OrderItemPropertyDto, OrderItemPropertyEntity> {
    public OrderItemPropertyConverter() {
        super(OrderItemPropertyEntity.class);
    }
}
