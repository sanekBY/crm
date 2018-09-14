package by.shalukho.service;

import by.shalukho.converter.CustomerOrderConverter;
import by.shalukho.dto.CustomerOrderDto;
import by.shalukho.entity.CustomerOrderEntity;
import by.shalukho.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService
        extends AbstractService<CustomerOrderDto, CustomerOrderEntity, CustomerOrderRepository> {

    public CustomerOrderService(CustomerOrderRepository CustomerOrderRepository,
                                CustomerOrderConverter CustomerOrderConverter) {
        super(CustomerOrderRepository, CustomerOrderConverter);
    }

    @Override
    public Optional<CustomerOrderEntity> findByActiveAndId(boolean active, Long id) {
        return getRepository().findByActiveAndId(active, id);
    }

    @Override
    public List<CustomerOrderEntity> findAllByActive(boolean active) {
        return getRepository().findAllByActive(active);
    }

    @Override
    protected void beforeEntitySave(CustomerOrderEntity customerOrderEntity) {
        if (customerOrderEntity.getCreatedOn() == null) {
            customerOrderEntity.setCreatedOn(LocalDateTime.now());
        }
        customerOrderEntity.setModifiedOn(LocalDateTime.now());
    }
}
