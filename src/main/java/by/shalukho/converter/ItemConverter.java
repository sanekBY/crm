package by.shalukho.converter;

import by.shalukho.dto.ItemDto;
import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemEntity;
import by.shalukho.entity.ItemPropertyEntity;
import by.shalukho.entity.ItemTypeEntity;
import by.shalukho.service.ItemTypeService;
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
            final Long itemTypeId = itemDto.getItemType().getId();
            final ItemTypeDto itemType = itemTypeService.findById(itemTypeId);
            final ItemTypeEntity itemTypeEntity = itemTypeConverter.convertToEntity(itemType);

            itemEntity.setItemType(itemTypeEntity);
        }
        if (itemDto.getItemProperties() != null) {
            final List<ItemPropertyEntity> itemTypeProperties =
                    itemPropertyConverter
                            .convertAllToEntity(itemDto.getItemProperties());
            itemEntity.setItemProperties(itemTypeProperties);
        }
        return itemEntity;
    }

    @Override
    protected ItemDto extraConvertToDto(final ItemEntity itemEntity, final ItemDto itemDto) {

        final ItemTypeEntity itemType = itemEntity.getItemType();
        itemDto.setItemType(itemTypeConverter.convertToDto(itemType));

        if (itemEntity.getItemProperties() != null) {
            itemDto.setItemProperties(itemPropertyConverter.convertAllToDto(itemEntity.getItemProperties()));
        }

        return itemDto;
    }

}
