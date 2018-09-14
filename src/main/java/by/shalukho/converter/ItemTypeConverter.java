package by.shalukho.converter;

import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemPropertyEntity;
import by.shalukho.entity.ItemTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypeConverter extends GenericConverter<ItemTypeDto, ItemTypeEntity> {

    @Autowired
    public ItemPropertyConverter itemPropertyConverter;


    public ItemTypeConverter() {
        super(ItemTypeEntity.class);
    }

    @Override
    protected ItemTypeDto extraConvertToDto(final ItemTypeEntity itemTypeEntity,
                                            final ItemTypeDto itemTypeDto) {
        if (itemTypeEntity.getItemProperties() != null) {
            itemTypeDto.setItemProperties(
                    itemPropertyConverter.convertAllToDto(itemTypeEntity.getItemProperties()));
        }
        return itemTypeDto;
    }

    @Override
    protected ItemTypeEntity extraConvertToEntity(final ItemTypeDto itemTypeDto,
                                                  final ItemTypeEntity itemTypeEntity) {

        if (itemTypeDto.getItemProperties() != null) {
            final List<ItemPropertyEntity> itemTypeProperties =
                    itemPropertyConverter
                            .convertAllToEntity(itemTypeDto.getItemProperties());

            itemTypeEntity.setItemProperties(itemTypeProperties);
        }

        return itemTypeEntity;
    }

}

