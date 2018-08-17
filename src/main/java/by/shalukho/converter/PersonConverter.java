package by.shalukho.converter;

import by.shalukho.dbo.PersonEntity;
import by.shalukho.dto.PersonDto;
import by.shalukho.enums.PhoneTypeEnum;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class PersonConverter extends GenericConverter<PersonDto, PersonEntity> {
    @Override
    protected BiFunction<PersonDto, PersonEntity, PersonEntity> getEntityToDtoFunction() {
        BiFunction<PersonDto, PersonEntity, PersonEntity> function = (dto, entity) -> {
            PhoneTypeEnum en;

            switch (dto.getPhoneType().toLowerCase()) {
                case "home":
                    en = PhoneTypeEnum.HOME;
                    break;
                case "work":
                    en = PhoneTypeEnum.WORK;
                    break;
                default:
                    en = PhoneTypeEnum.MOBILE;
            }
            entity.setPhoneType(en);
            return entity;
        };
        return function;
    }

}
