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
public class ItemPropertyService extends AbstractService<ItemPropertyDto, ItemPropertyEntity> {

    public ItemPropertyService(ItemPropertyRepository itemPropertyRepository,
                               ItemPropertyConverter itemPropertyConverter) {
        super(itemPropertyRepository, itemPropertyConverter);
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
