package by.shalukho.service;

import by.shalukho.converter.ItemConverter;
import by.shalukho.dto.ItemDto;
import by.shalukho.entity.ItemEntity;
import by.shalukho.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService extends AbstractUniqueNameService<ItemDto, ItemEntity, ItemRepository> {

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

    @Override
    public Optional<ItemEntity> findByName(final String name) {
        return getRepository().findByName(name);
    }

    @Override
    public Page<ItemEntity> findAllByActiveIsTrue(final Pageable pageable) {
        return getRepository().findAllByActiveIsTrue(pageable);
    }
}
