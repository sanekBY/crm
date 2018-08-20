package by.shalukho.service;

import by.shalukho.converter.ItemConverter;
import by.shalukho.dto.ItemDto;
import by.shalukho.entity.ItemEntity;
import by.shalukho.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends AbstractService<ItemDto, ItemEntity> {

    public ItemService(ItemRepository itemRepository, ItemConverter itemConverter) {
        super(itemRepository, itemConverter, ItemDto.class, ItemEntity.class);
    }

}
