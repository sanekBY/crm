package by.shalukho.converter.customer;

import by.shalukho.converter.GenericConverterWithEnums;
import by.shalukho.dto.customer.ContactDataDto;
import by.shalukho.entity.customer.ContactDataEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactDataConverter extends GenericConverterWithEnums<ContactDataDto, ContactDataEntity> {

    public ContactDataConverter() {
        super(ContactDataEntity.class);
    }

}
