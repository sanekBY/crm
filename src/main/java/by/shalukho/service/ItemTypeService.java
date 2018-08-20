package by.shalukho.service;

import by.shalukho.converter.ItemTypeConverter;
import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemTypeEntity;
import by.shalukho.repository.ItemTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeService extends AbstractService<ItemTypeDto, ItemTypeEntity> {

    public ItemTypeService(ItemTypeRepository itemTypeRepository, ItemTypeConverter itemTypeConverter) {
        super(itemTypeRepository, itemTypeConverter, ItemTypeDto.class, ItemTypeEntity.class);
    }

}
