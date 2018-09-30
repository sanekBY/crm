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
    public Optional<ItemTypeEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<ItemTypeEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }
}
