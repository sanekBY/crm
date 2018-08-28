package by.shalukho.converter.customer;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.entity.customer.AddressEntity;
import by.shalukho.entity.customer.ContactDataEntity;
import by.shalukho.entity.customer.CustomerEntity;
import by.shalukho.enums.CustomerTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@Service
public class CustomerConverter extends GenericConverter<CustomerDto, CustomerEntity> {

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private ContactDataConverter contactDataConverter;


    public CustomerConverter() {
        super(CustomerDto.class, CustomerEntity.class);
    }

    @Override
    protected BiFunction<CustomerDto, CustomerEntity, CustomerEntity> getDtoToEntityFunction() {
        BiFunction<CustomerDto, CustomerEntity, CustomerEntity> function = (customerDto, customerEntity) -> {

            List<AddressEntity> addresses = addressConverter.convertAllToEntity(customerDto.getAddresses());
            List<ContactDataEntity> contacts = contactDataConverter.convertAllToEntity(customerDto.getContacts());

            if (addresses != null) {
                addresses.stream().forEach(a -> a.setCustomer(customerEntity));
                customerEntity.setAddresses(addresses);
            }
            if (contacts != null) {
                contacts.stream().forEach(c -> c.setCustomer(customerEntity));
                customerEntity.setContacts(contacts);
            }

            if (customerDto.getType().equals(CustomerTypeEnum.COMPANY.toString())) {
                customerEntity.setCustomerType(CustomerTypeEnum.COMPANY);
            } else {
                customerEntity.setCustomerType(CustomerTypeEnum.PERSON);
            }

            return customerEntity;
        };
        return function;
    }

    @Override
    protected BiFunction<CustomerEntity, CustomerDto, CustomerDto> getEntityToDtoFunction() {
        BiFunction<CustomerEntity, CustomerDto, CustomerDto> function = (customerEntity, customerDto) -> {

            customerDto.setAddresses(addressConverter.convertAllToDto(customerEntity.getAddresses()));
            customerDto.setContacts(contactDataConverter.convertAllToDto(customerEntity.getContacts()));
            customerDto.setType(customerEntity.getCustomerType().toString());

            return customerDto;
        };
        return function;
    }
}
