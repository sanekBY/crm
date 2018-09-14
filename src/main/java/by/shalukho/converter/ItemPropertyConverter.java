package by.shalukho.converter;

import by.shalukho.dto.ItemPropertyDto;
import by.shalukho.entity.ItemPropertyEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemPropertyConverter extends GenericConverter<ItemPropertyDto, ItemPropertyEntity> {
    public ItemPropertyConverter() {
        super(ItemPropertyEntity.class);
    }
}
