package by.shalukho.converter.order;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.order.OrderItemDto;
import by.shalukho.entity.order.OrderItemEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemConverter extends GenericConverter<OrderItemDto, OrderItemEntity> {
    public OrderItemConverter() {
        super(OrderItemDto.class, OrderItemEntity.class);
    }
}
