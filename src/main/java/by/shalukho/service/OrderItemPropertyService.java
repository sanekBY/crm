package by.shalukho.service;

import by.shalukho.converter.OrderItemPropertyConverter;
import by.shalukho.dto.OrderItemPropertyDto;
import by.shalukho.entity.OrderItemPropertyEntity;
import by.shalukho.repository.OrderItemPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemPropertyService
        extends AbstractService<OrderItemPropertyDto, OrderItemPropertyEntity, OrderItemPropertyRepository> {

    public OrderItemPropertyService(OrderItemPropertyRepository orderItemPropertyRepository,
                                    OrderItemPropertyConverter orderItemPropertyConverter) {
        super(orderItemPropertyRepository, orderItemPropertyConverter);
    }

    @Override
    public Optional<OrderItemPropertyEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override public List<OrderItemPropertyEntity> findAllByActiveIsTrue() {
        return null;
    }
}
