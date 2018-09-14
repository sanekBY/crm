package by.shalukho.service;

import by.shalukho.converter.ItemTypeConverter;
import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemTypeEntity;
import by.shalukho.repository.ItemTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemTypeService extends AbstractService<ItemTypeDto, ItemTypeEntity, ItemTypeRepository> {

    public ItemTypeService(ItemTypeRepository itemTypeRepository, ItemTypeConverter itemTypeConverter) {
        super(itemTypeRepository, itemTypeConverter);
    }

    @Override
    public Optional<ItemTypeEntity> findByActiveAndId(boolean active, Long id) {
        return getRepository().findByActiveAndId(active, id);
    }

    @Override
    public List<ItemTypeEntity> findAllByActive(boolean active) {
        return getRepository().findAllByActive(active);
    }
}
