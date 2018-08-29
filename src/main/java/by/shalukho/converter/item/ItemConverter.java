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

@Service
public class ItemConverter extends GenericConverter<ItemDto, ItemEntity> {

    @Autowired
    public ItemTypeService itemTypeService;

    @Autowired
    public ItemTypeConverter itemTypeConverter;

    @Autowired
    public ItemPropertyConverter itemPropertyConverter;

    public ItemConverter() {
        super(ItemEntity.class);
    }

    @Override
    protected ItemEntity extraConvertToEntity(final ItemDto itemDto, final ItemEntity itemEntity) {
        if (itemDto.getItemType() != null) {
            Long itemTypeId = itemDto.getItemType().getId();
            ItemTypeDto itemType = itemTypeService.findById(itemTypeId);
            ItemTypeEntity itemTypeEntity = itemTypeConverter.convertToEntity(itemType);

            itemEntity.setItemType(itemTypeEntity);
        }
        if (itemDto.getItemProperties() != null) {
            List<ItemPropertyEntity> itemTypeProperties =
                    itemPropertyConverter
                            .convertAllToEntity(itemDto.getItemProperties());
            itemEntity.setItemProperties(itemTypeProperties);
        }
        return itemEntity;
    }

    @Override
    protected ItemDto extraConvertToDto(final ItemEntity itemEntity, final ItemDto itemDto) {

        ItemTypeEntity itemType = itemEntity.getItemType();
        itemDto.setItemType(itemTypeConverter.convertToDto(itemType));

        if (itemEntity.getItemProperties() != null) {
            itemDto.setItemProperties(itemPropertyConverter.convertAllToDto(itemEntity.getItemProperties()));
        }

        return itemDto;
    }

}
