package by.shalukho.service;

import by.shalukho.converter.CustomerOrderConverter;
import by.shalukho.dto.CustomerOrderDto;
import by.shalukho.entity.CustomerOrderEntity;
import by.shalukho.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerOrderService
        extends AbstractService<CustomerOrderDto, CustomerOrderEntity, CustomerOrderRepository> {

    public CustomerOrderService(CustomerOrderRepository CustomerOrderRepository,
                                CustomerOrderConverter CustomerOrderConverter) {
        super(CustomerOrderRepository, CustomerOrderConverter);
    }

    @Override
    protected void beforeEntitySave(CustomerOrderEntity customerOrderEntity) {
        if (customerOrderEntity.getCreatedOn() == null) {
            customerOrderEntity.setCreatedOn(LocalDateTime.now());
        }
        customerOrderEntity.setModifiedOn(LocalDateTime.now());
    }

}
