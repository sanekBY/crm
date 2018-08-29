package by.shalukho.converter.item;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.item.ItemPropertyDto;
import by.shalukho.entity.items.ItemPropertyEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemPropertyConverter extends GenericConverter<ItemPropertyDto, ItemPropertyEntity> {
    public ItemPropertyConverter() {
        super(ItemPropertyEntity.class);
    }
}
