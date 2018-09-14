package by.shalukho.converter;

import by.shalukho.dto.AddressDto;
import by.shalukho.entity.AddressEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressConverter extends GenericConverter<AddressDto, AddressEntity> {

    public AddressConverter() {
        super(AddressEntity.class);
    }
}
