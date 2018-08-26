package by.shalukho.converter.item;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.item.ItemTypePropertyDto;
import by.shalukho.entity.items.ItemTypePropertyEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemTypePropertyConverter extends GenericConverter<ItemTypePropertyDto, ItemTypePropertyEntity> {
    public ItemTypePropertyConverter() {
        super(ItemTypePropertyDto.class, ItemTypePropertyEntity.class);
    }
}
