package by.shalukho.converter;

import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemTypeEntity;
import by.shalukho.entity.ItemTypePropertyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@Service
public class ItemTypeConverter extends GenericConverter<ItemTypeDto, ItemTypeEntity> {

    @Autowired
    public ItemTypePropertyConverter itemTypePropertyConverter;


    public ItemTypeConverter() {
        super(ItemTypeDto.class, ItemTypeEntity.class);
    }

    @Override protected BiFunction<ItemTypeDto, ItemTypeEntity, ItemTypeEntity> getDtoToEntityFunction() {
        BiFunction<ItemTypeDto, ItemTypeEntity, ItemTypeEntity> function = (itemTypeDto, itemTypeEntity) -> {

            if (itemTypeDto.getItemTypeProperties() != null) {
                List<ItemTypePropertyEntity> itemTypeProperties =
                        itemTypePropertyConverter
                                .convertAllToEntity(itemTypeDto.getItemTypeProperties());

                itemTypeEntity.setItemTypeProperties(itemTypeProperties);
            }

            return itemTypeEntity;
        };
        return function;
    }

    @Override protected BiFunction<ItemTypeEntity, ItemTypeDto, ItemTypeDto> getEntityToDtoFunction() {
        BiFunction<ItemTypeEntity, ItemTypeDto, ItemTypeDto> function = (itemTypeEntity, itemTypeDto) -> {
            if (itemTypeEntity.getItemTypeProperties() != null) {
                itemTypeDto.setItemTypeProperties(
                        itemTypePropertyConverter.convertAllToDto(itemTypeEntity.getItemTypeProperties()));
            }
            return itemTypeDto;
        };
        return function;
    }
}

