package by.shalukho.service.order;

import by.shalukho.converter.order.OrderItemTypeConverter;
import by.shalukho.dto.order.OrderItemTypeDto;
import by.shalukho.entity.order.OrderItemTypeEntity;
import by.shalukho.repository.OrderItemTypeRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemTypeService extends AbstractService<OrderItemTypeDto, OrderItemTypeEntity> {

    public OrderItemTypeService(OrderItemTypeRepository orderItemTypeRepository,
                                OrderItemTypeConverter orderItemTypeConverter) {
        super(orderItemTypeRepository, orderItemTypeConverter, OrderItemTypeDto.class, OrderItemTypeEntity.class);
    }

    @Override
    public Optional<OrderItemTypeEntity> findByActiveAndId(boolean active, Long id) {
        return ((OrderItemTypeRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<OrderItemTypeEntity> findAllByActive(boolean active) {
        return ((OrderItemTypeRepository) getRepository()).findAllByActive(active);
    }
}
