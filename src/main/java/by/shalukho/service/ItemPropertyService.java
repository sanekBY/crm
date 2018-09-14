package by.shalukho.service;

import by.shalukho.converter.ItemPropertyConverter;
import by.shalukho.dto.ItemPropertyDto;
import by.shalukho.entity.ItemPropertyEntity;
import by.shalukho.repository.ItemPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPropertyService extends AbstractService<ItemPropertyDto, ItemPropertyEntity, ItemPropertyRepository> {

    public ItemPropertyService(ItemPropertyRepository itemPropertyRepository,
                               ItemPropertyConverter itemPropertyConverter) {
        super(itemPropertyRepository, itemPropertyConverter);
    }

    @Override
    public Optional<ItemPropertyEntity> findByActiveAndId(boolean active, Long id) {
        return getRepository().findByActiveAndId(active, id);
    }

    @Override
    public List<ItemPropertyEntity> findAllByActive(boolean active) {
        return getRepository().findAllByActive(active);
    }
}
