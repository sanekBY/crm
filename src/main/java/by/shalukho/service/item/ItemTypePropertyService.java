package by.shalukho.service.item;

import by.shalukho.converter.item.ItemPropertyConverter;
import by.shalukho.dto.item.ItemPropertyDto;
import by.shalukho.entity.items.ItemPropertyEntity;
import by.shalukho.repository.ItemPropertyRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemTypePropertyService extends AbstractService<ItemPropertyDto, ItemPropertyEntity> {

    public ItemTypePropertyService(ItemPropertyRepository itemPropertyRepository,
                                   ItemPropertyConverter itemPropertyConverter) {
        super(itemPropertyRepository, itemPropertyConverter, ItemPropertyDto.class,
              ItemPropertyEntity.class);
    }

    @Override
    public Optional<ItemPropertyEntity> findByActiveAndId(boolean active, Long id) {
        return ((ItemPropertyRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<ItemPropertyEntity> findAllByActive(boolean active) {
        return ((ItemPropertyRepository) getRepository()).findAllByActive(active);
    }
}
