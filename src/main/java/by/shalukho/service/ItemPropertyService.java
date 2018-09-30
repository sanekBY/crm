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
    public Optional<ItemPropertyEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<ItemPropertyEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }
}
