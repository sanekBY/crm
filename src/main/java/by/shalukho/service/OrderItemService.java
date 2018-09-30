package by.shalukho.service;

import by.shalukho.converter.OrderItemConverter;
import by.shalukho.dto.OrderItemDto;
import by.shalukho.entity.OrderItemEntity;
import by.shalukho.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService extends AbstractService<OrderItemDto, OrderItemEntity, OrderItemRepository> {

    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemConverter orderItemConverter) {
        super(orderItemRepository, orderItemConverter);
    }

    @Override
    public Optional<OrderItemEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<OrderItemEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }
}
