package by.shalukho.converter.item;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.item.ItemDto;
import by.shalukho.dto.item.ItemTypeDto;
import by.shalukho.entity.items.ItemEntity;
import by.shalukho.entity.items.ItemPropertyEntity;
import by.shalukho.entity.items.ItemTypeEntity;
import by.shalukho.service.item.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@Service
public class ItemConverter extends GenericConverter<ItemDto, ItemEntity> {

    @Autowired
    public ItemTypeService itemTypeService;

    @Autowired
    public ItemTypeConverter itemTypeConverter;

    @Autowired
    public ItemPropertyConverter itemPropertyConverter;

    public ItemConverter() {
        super(ItemDto.class, ItemEntity.class);
    }

    @Override
    protected BiFunction<ItemDto, ItemEntity, ItemEntity> getDtoToEntityFunction() {
        BiFunction<ItemDto, ItemEntity, ItemEntity> function = (dto, entity) -> {
            
            if (dto.getItemType() != null) {
                Long itemTypeId = dto.getItemType().getId();
                ItemTypeDto itemType = itemTypeService.findById(itemTypeId);
                ItemTypeEntity itemTypeEntity = itemTypeConverter.convertToEntity(itemType);

                entity.setItemType(itemTypeEntity);
            }
            if (dto.getItemProperties() != null) {
                List<ItemPropertyEntity> itemTypeProperties =
                        itemPropertyConverter
                                .convertAllToEntity(dto.getItemProperties());
                entity.setItemProperties(itemTypeProperties);
            }
            return entity;
        };
        return function;
    }

    @Override
    protected BiFunction<ItemEntity, ItemDto, ItemDto> getEntityToDtoFunction() {
        BiFunction<ItemEntity, ItemDto, ItemDto> function = (itemEntity, itemDto) -> {

            ItemTypeEntity itemType = itemEntity.getItemType();
            itemDto.setItemType(itemTypeConverter.convertToDto(itemType));

            if (itemEntity.getItemProperties() != null) {
                itemDto.setItemProperties(itemPropertyConverter.convertAllToDto(itemEntity.getItemProperties()));
            }

            return itemDto;
        };
        return function;
    }

}
