package by.shalukho.converter;

import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.AddressEntity;
import by.shalukho.entity.ContactDataEntity;
import by.shalukho.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerConverter extends GenericConverterWithEnums<CustomerDto, CustomerEntity> {

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private ContactDataConverter contactDataConverter;


    public CustomerConverter() {
        super(CustomerEntity.class);
    }

    @Override
    protected CustomerDto extraConvertToDto(final CustomerEntity customerEntity,
                                            final CustomerDto customerDto) {
        customerDto.setAddresses(addressConverter.convertAllToDto(customerEntity.getAddresses()));
        customerDto.setContacts(contactDataConverter.convertAllToDto(customerEntity.getContacts()));

        return customerDto;
    }

    @Override
    protected CustomerEntity extraConvertToEntity(final CustomerDto customerDto,
                                                  final CustomerEntity customerEntity) {
        final List<AddressEntity> addresses = addressConverter.convertAllToEntity(customerDto.getAddresses());
        final List<ContactDataEntity> contacts = contactDataConverter.convertAllToEntity(customerDto.getContacts());

        if (addresses != null) {
            addresses.stream().forEach(a -> a.setCustomer(customerEntity));
            customerEntity.setAddresses(addresses);
        }
        if (contacts != null) {
            contacts.stream().forEach(c -> c.setCustomer(customerEntity));
            customerEntity.setContacts(contacts);
        }

        return customerEntity;
    }

}
