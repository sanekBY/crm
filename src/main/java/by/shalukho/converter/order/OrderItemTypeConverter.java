package by.shalukho.converter.order;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.order.OrderItemTypeDto;
import by.shalukho.entity.order.OrderItemTypeEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemTypeConverter extends GenericConverter<OrderItemTypeDto, OrderItemTypeEntity> {
    public OrderItemTypeConverter() {
        super(OrderItemTypeDto.class, OrderItemTypeEntity.class);
    }
}
