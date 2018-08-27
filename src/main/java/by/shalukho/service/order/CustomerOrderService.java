package by.shalukho.service.order;

import by.shalukho.converter.order.CustomerOrderConverter;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.entity.order.CustomerOrderEntity;
import by.shalukho.repository.CustomerOrderRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService extends AbstractService<CustomerOrderDto, CustomerOrderEntity> {

    public CustomerOrderService(CustomerOrderRepository CustomerOrderRepository,
                                CustomerOrderConverter CustomerOrderConverter) {
        super(CustomerOrderRepository, CustomerOrderConverter, CustomerOrderDto.class, CustomerOrderEntity.class);
    }

    @Override
    public Optional<CustomerOrderEntity> findByActiveAndId(boolean active, Long id) {
        return ((CustomerOrderRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<CustomerOrderEntity> findAllByActive(boolean active) {
        return ((CustomerOrderRepository) getRepository()).findAllByActive(active);
    }
}
