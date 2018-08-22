package by.shalukho.converter;

import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemEntity;
import by.shalukho.entity.ItemTypeEntity;
import by.shalukho.entity.ItemTypePropertyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.BiFunction;

@Service
public class ItemTypeConverter extends GenericConverter<ItemTypeDto, ItemTypeEntity> {
    public ItemTypeConverter() {
        super(ItemTypeDto.class, ItemTypeEntity.class);
    }

}

