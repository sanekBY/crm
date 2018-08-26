package by.shalukho.converter.item;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.item.ItemTypeDto;
import by.shalukho.entity.items.ItemPropertyEntity;
import by.shalukho.entity.items.ItemTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@Service
public class ItemTypeConverter extends GenericConverter<ItemTypeDto, ItemTypeEntity> {

    @Autowired
    public ItemPropertyConverter itemPropertyConverter;


    public ItemTypeConverter() {
        super(ItemTypeDto.class, ItemTypeEntity.class);
    }

    @Override protected BiFunction<ItemTypeDto, ItemTypeEntity, ItemTypeEntity> getDtoToEntityFunction() {
        BiFunction<ItemTypeDto, ItemTypeEntity, ItemTypeEntity> function = (itemTypeDto, itemTypeEntity) -> {

            if (itemTypeDto.getItemProperties() != null) {
                List<ItemPropertyEntity> itemTypeProperties =
                        itemPropertyConverter
                                .convertAllToEntity(itemTypeDto.getItemProperties());

                itemTypeEntity.setItemProperties(itemTypeProperties);
            }

            return itemTypeEntity;
        };
        return function;
    }

    @Override protected BiFunction<ItemTypeEntity, ItemTypeDto, ItemTypeDto> getEntityToDtoFunction() {
        BiFunction<ItemTypeEntity, ItemTypeDto, ItemTypeDto> function = (itemTypeEntity, itemTypeDto) -> {
            if (itemTypeEntity.getItemProperties() != null) {
                itemTypeDto.setItemProperties(
                        itemPropertyConverter.convertAllToDto(itemTypeEntity.getItemProperties()));
            }
            return itemTypeDto;
        };
        return function;
    }
}

