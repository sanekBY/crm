package by.shalukho.controllers.item;

import by.shalukho.dto.customer.AddressDto;
import by.shalukho.dto.customer.ContactDataDto;
import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.enums.CustomerTypeEnum;
import by.shalukho.enums.PhoneTypeEnum;

import java.util.ArrayList;

public abstract class AbstractCustomerTest extends AbstractTest {

    public static final String API_CUSTOMER_WITHOUT_ID = "/customer";
    public static final String CUSTOMER_NAME = "Ivan Ivanov";
    public static final String CUSTOMER_EMAIL = "customer@gmail.com";
    public static final String CUSTOMER_TYPE = CustomerTypeEnum.COMPANY.toString();
    public static final String CUSTOMER_ADDRESS = "Nezalezhnasti pr.";
    public static final String CUSTOMER_CITY = "Minsk";
    public static final String CUSTOMER_STATE = "Minsk vobl.";
    public static final String CUSTOMER_POST_CODE = "220056";
    public static final String CUSTOMER_PHONE = "+375291820620";
    public static final String CUSTOMER_PHONE_TYPE = PhoneTypeEnum.MOBILE.toString();

    protected CustomerDto createCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(ID);
        customerDto.setName(CUSTOMER_NAME);
        customerDto.setEmail(CUSTOMER_EMAIL);
        customerDto.setType(CUSTOMER_TYPE);
        customerDto.setAddresses(new ArrayList<>());
        customerDto.setContacts(new ArrayList<>());
        return customerDto;
    }

    protected AddressDto createAddress() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(ID);
        addressDto.setAddress(CUSTOMER_ADDRESS);
        addressDto.setCity(CUSTOMER_CITY);
        addressDto.setState(CUSTOMER_STATE);
        addressDto.setPostalCode(CUSTOMER_POST_CODE);
        return addressDto;
    }

    protected ContactDataDto createContacts() {
        ContactDataDto contactDataDto = new ContactDataDto();
        contactDataDto.setId(ID);
        contactDataDto.setPhone(CUSTOMER_PHONE);
        contactDataDto.setPhoneType(CUSTOMER_PHONE_TYPE);
        return contactDataDto;
    }
}
