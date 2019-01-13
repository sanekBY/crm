package by.shalukho.service;

import by.shalukho.converter.ItemPropertyConverter;
import by.shalukho.dto.ItemPropertyDto;
import by.shalukho.entity.ItemPropertyEntity;
import by.shalukho.repository.ItemPropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemPropertyService extends AbstractService<ItemPropertyDto, ItemPropertyEntity, ItemPropertyRepository> {

    public ItemPropertyService(ItemPropertyRepository itemPropertyRepository,
                               ItemPropertyConverter itemPropertyConverter) {
        super(itemPropertyRepository, itemPropertyConverter);
    }

}
