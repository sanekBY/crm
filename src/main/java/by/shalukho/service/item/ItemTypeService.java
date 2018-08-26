package by.shalukho.service.item;

import by.shalukho.converter.item.ItemTypeConverter;
import by.shalukho.dto.item.ItemTypeDto;
import by.shalukho.entity.items.ItemTypeEntity;
import by.shalukho.repository.ItemTypeRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemTypeService extends AbstractService<ItemTypeDto, ItemTypeEntity> {

    public ItemTypeService(ItemTypeRepository itemTypeRepository, ItemTypeConverter itemTypeConverter) {
        super(itemTypeRepository, itemTypeConverter, ItemTypeDto.class, ItemTypeEntity.class);
    }

    @Override
    public Optional<ItemTypeEntity> findByActiveAndId(boolean active, Long id) {
        return ((ItemTypeRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<ItemTypeEntity> findAllByActive(boolean active) {
        return ((ItemTypeRepository) getRepository()).findAllByActive(active);
    }
}
