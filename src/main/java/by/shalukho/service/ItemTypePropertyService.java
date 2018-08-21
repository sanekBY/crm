package by.shalukho.service;

import by.shalukho.converter.ItemTypePropertyConverter;
import by.shalukho.dto.ItemTypePropertyDto;
import by.shalukho.entity.ItemTypePropertyEntity;
import by.shalukho.repository.ItemTypePropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypePropertyService extends AbstractService<ItemTypePropertyDto, ItemTypePropertyEntity> {

    public ItemTypePropertyService(ItemTypePropertyRepository itemTypePropertyRepository, ItemTypePropertyConverter itemTypePropertyConverter) {
        super(itemTypePropertyRepository, itemTypePropertyConverter, ItemTypePropertyDto.class, ItemTypePropertyEntity.class);
    }

    @Override
    public ItemTypePropertyEntity findByActiveAndId(boolean active, Long id) {
        return ((ItemTypePropertyRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<ItemTypePropertyEntity> findAllByActive(boolean active) {
        return ((ItemTypePropertyRepository) getRepository()).findAllByActive(active);
    }
}
