package by.shalukho.service.order;

import by.shalukho.converter.order.OrderItemConverter;
import by.shalukho.dto.order.OrderItemDto;
import by.shalukho.entity.order.OrderItemEntity;
import by.shalukho.repository.OrderItemRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService extends AbstractService<OrderItemDto, OrderItemEntity> {

    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemConverter orderItemConverter) {
        super(orderItemRepository, orderItemConverter, OrderItemDto.class, OrderItemEntity.class);
    }

    @Override
    public Optional<OrderItemEntity> findByActiveAndId(boolean active, Long id) {
        return ((OrderItemRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<OrderItemEntity> findAllByActive(boolean active) {
        return ((OrderItemRepository) getRepository()).findAllByActive(active);
    }
}
