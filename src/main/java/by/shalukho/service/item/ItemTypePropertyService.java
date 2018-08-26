package by.shalukho.service.item;

import by.shalukho.converter.item.ItemTypePropertyConverter;
import by.shalukho.dto.item.ItemTypePropertyDto;
import by.shalukho.entity.items.ItemTypePropertyEntity;
import by.shalukho.repository.ItemTypePropertyRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemTypePropertyService extends AbstractService<ItemTypePropertyDto, ItemTypePropertyEntity> {

    public ItemTypePropertyService(ItemTypePropertyRepository itemTypePropertyRepository,
                                   ItemTypePropertyConverter itemTypePropertyConverter) {
        super(itemTypePropertyRepository, itemTypePropertyConverter, ItemTypePropertyDto.class,
              ItemTypePropertyEntity.class);
    }

    @Override
    public Optional<ItemTypePropertyEntity> findByActiveAndId(boolean active, Long id) {
        return ((ItemTypePropertyRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<ItemTypePropertyEntity> findAllByActive(boolean active) {
        return ((ItemTypePropertyRepository) getRepository()).findAllByActive(active);
    }
}
