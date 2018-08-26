package by.shalukho.service.item;

import by.shalukho.converter.item.ItemConverter;
import by.shalukho.dto.item.ItemDto;
import by.shalukho.entity.items.ItemEntity;
import by.shalukho.repository.ItemRepository;
import by.shalukho.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService extends AbstractService<ItemDto, ItemEntity> {

    public ItemService(ItemRepository itemRepository, ItemConverter itemConverter) {
        super(itemRepository, itemConverter, ItemDto.class, ItemEntity.class);
    }

    @Override
    public Optional<ItemEntity> findByActiveAndId(boolean active, Long id) {
        return ((ItemRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<ItemEntity> findAllByActive(boolean active) {
        return ((ItemRepository) getRepository()).findAllByActive(active);
    }
}
