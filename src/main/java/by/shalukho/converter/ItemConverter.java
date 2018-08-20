package by.shalukho.converter;

import by.shalukho.dto.ItemDto;
import by.shalukho.entity.ItemEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemConverter extends GenericConverter<ItemDto, ItemEntity> {
}
