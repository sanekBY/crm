package by.shalukho.converter;

import by.shalukho.dto.ItemDto;
import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemEntity;
import by.shalukho.entity.ItemTypeEntity;
import by.shalukho.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class ItemConverter extends GenericConverter<ItemDto, ItemEntity> {

    @Autowired
    ItemTypeService itemTypeService;

    @Override
    protected BiFunction<ItemDto, ItemEntity, ItemEntity> getDtoToEntityFunction() {
        BiFunction<ItemDto, ItemEntity, ItemEntity> function = (dto, entity) -> {
            if (dto.getItemType() != null) {
                DtoDboConverter<ItemTypeDto, ItemTypeEntity> itemTypeConverter = itemTypeService.getConverter();
                Long itemTypeId = dto.getItemType().getId();
                ItemTypeEntity itemTypeEntity = itemTypeConverter.convertToEntity(itemTypeService.findById(itemTypeId));
                entity.setItemType(itemTypeEntity);
            }
            return entity;
        };
        return function;
    }

}
