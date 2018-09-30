package by.shalukho.service;

import by.shalukho.converter.ItemConverter;
import by.shalukho.dto.ItemDto;
import by.shalukho.entity.ItemEntity;
import by.shalukho.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService extends AbstractService<ItemDto, ItemEntity, ItemRepository> {

    public ItemService(ItemRepository itemRepository, ItemConverter itemConverter) {
        super(itemRepository, itemConverter);
    }

    @Override
    public Optional<ItemEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<ItemEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }
}
