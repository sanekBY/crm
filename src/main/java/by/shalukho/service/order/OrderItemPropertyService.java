package by.shalukho.service.order;

import by.shalukho.converter.order.OrderItemPropertyConverter;
import by.shalukho.dto.order.OrderItemPropertyDto;
import by.shalukho.entity.order.OrderItemPropertyEntity;
import by.shalukho.repository.OrderItemPropertyRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemPropertyService extends AbstractService<OrderItemPropertyDto, OrderItemPropertyEntity> {

    public OrderItemPropertyService(OrderItemPropertyRepository orderItemPropertyRepository,
                                    OrderItemPropertyConverter orderItemPropertyConverter) {
        super(orderItemPropertyRepository, orderItemPropertyConverter);
    }

    @Override
    public Optional<OrderItemPropertyEntity> findByActiveAndId(boolean active, Long id) {
        return ((OrderItemPropertyRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<OrderItemPropertyEntity> findAllByActive(boolean active) {
        return ((OrderItemPropertyRepository) getRepository()).findAllByActive(active);
    }
}
