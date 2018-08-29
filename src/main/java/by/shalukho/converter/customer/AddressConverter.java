package by.shalukho.converter.customer;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.customer.AddressDto;
import by.shalukho.entity.customer.AddressEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressConverter extends GenericConverter<AddressDto, AddressEntity> {

    public AddressConverter() {
        super(AddressEntity.class);
    }
}
