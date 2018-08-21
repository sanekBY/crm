package by.shalukho.converter;

import by.shalukho.dto.ItemTypeDto;
import by.shalukho.entity.ItemTypeEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.function.BiFunction;

@Service
public class ItemTypeConverter extends GenericConverter<ItemTypeDto, ItemTypeEntity> {

}
