package by.shalukho.service;

import by.shalukho.converter.OrderItemConverter;
import by.shalukho.dto.OrderItemDto;
import by.shalukho.entity.OrderItemEntity;
import by.shalukho.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends AbstractService<OrderItemDto, OrderItemEntity, OrderItemRepository> {

    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemConverter orderItemConverter) {
        super(orderItemRepository, orderItemConverter);
    }
}
