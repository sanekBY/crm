package by.shalukho.service;

import by.shalukho.converter.ItemTypeConverter;
import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemTypeEntity;
import by.shalukho.repository.ItemTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypeService extends AbstractService<ItemTypeDto, ItemTypeEntity> {

    public ItemTypeService(ItemTypeRepository itemTypeRepository, ItemTypeConverter itemTypeConverter) {
        super(itemTypeRepository, itemTypeConverter, ItemTypeDto.class, ItemTypeEntity.class);
    }

    @Override
    public ItemTypeEntity findByActiveAndId(boolean active, Long id) {
        return ((ItemTypeRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<ItemTypeEntity> findAllByActive(boolean active) {
        return ((ItemTypeRepository) getRepository()).findAllByActive(active);
    }
}
