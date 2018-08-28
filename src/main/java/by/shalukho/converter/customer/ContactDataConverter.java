package by.shalukho.converter.customer;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.customer.ContactDataDto;
import by.shalukho.entity.customer.ContactDataEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactDataConverter extends GenericConverter<ContactDataDto, ContactDataEntity> {

    public ContactDataConverter() {
        super(ContactDataDto.class, ContactDataEntity.class);
    }
}
