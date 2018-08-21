package by.shalukho.converter;

import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemTypeEntity;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class ItemTypeConverter extends GenericConverter<ItemTypeDto, ItemTypeEntity> {
    @Override
    protected BiFunction<ItemTypeEntity, ItemTypeDto, ItemTypeDto> getEntityToDtoFunction() {
        BiFunction<ItemTypeEntity, ItemTypeDto, ItemTypeDto> function = (entity, dto) -> {
            dto.setItems(null);
            dto.setItemTypeProperties(null);
            return dto;
        };
        return function;
    }
}
