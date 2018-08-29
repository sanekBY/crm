package by.shalukho.converter.customer;

import by.shalukho.converter.GenericConverterWithEnums;
import by.shalukho.dto.customer.ContactDataDto;
import by.shalukho.entity.customer.ContactDataEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactDataConverter extends GenericConverterWithEnums<ContactDataDto, ContactDataEntity> {

    public ContactDataConverter() {
        super(ContactDataDto.class, ContactDataEntity.class);
    }

//    @Override
//    protected BiFunction<ContactDataDto, ContactDataEntity, ContactDataEntity> getDtoToEntityFunction() {
//        BiFunction<ContactDataDto, ContactDataEntity, ContactDataEntity> function =
//                (contactDataDto, contactDataEntity) -> {
//                    final PhoneTypeEnum phoneType = getEnumValue(contactDataDto.getPhoneType(), PhoneTypeEnum.values());
//                    contactDataEntity.setPhoneType(phoneType);
//
//                    return contactDataEntity;
//                };
//        return function;
//    }
//
//    @Override
//    protected BiFunction<ContactDataEntity, ContactDataDto, ContactDataDto> getEntityToDtoFunction() {
//        BiFunction<ContactDataEntity, ContactDataDto, ContactDataDto> function = (contactDataEntity, contactDataDto) -> {
//            contactDataDto.setPhoneType(contactDataEntity.getPhoneType().toString());
//            return contactDataDto;
//        };
//        return function;
//    }
}
