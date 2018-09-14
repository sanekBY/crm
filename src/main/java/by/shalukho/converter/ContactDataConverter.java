package by.shalukho.converter;

import by.shalukho.dto.ContactDataDto;
import by.shalukho.entity.ContactDataEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactDataConverter extends GenericConverterWithEnums<ContactDataDto, ContactDataEntity> {

    public ContactDataConverter() {
        super(ContactDataEntity.class);
    }

}
