package by.shalukho.controller;

import by.shalukho.controller.AbstractControllerTest;
import by.shalukho.dto.AddressDto;
import by.shalukho.dto.ContactDataDto;
import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.CustomerTypeEnum;
import by.shalukho.entity.PhoneTypeEnum;

import java.util.ArrayList;

public abstract class AbstractCustomerTest extends AbstractControllerTest {

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
        customerDto.setId(RANDOM_VALUE);
        customerDto.setName(CUSTOMER_NAME);
        customerDto.setEmail(CUSTOMER_EMAIL);
        customerDto.setType(CUSTOMER_TYPE);
        customerDto.setAddresses(new ArrayList<>());
        customerDto.setContacts(new ArrayList<>());
        return customerDto;
    }

    protected AddressDto createAddress() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(RANDOM_VALUE);
        addressDto.setAddress(CUSTOMER_ADDRESS);
        addressDto.setCity(CUSTOMER_CITY);
        addressDto.setState(CUSTOMER_STATE);
        addressDto.setPostalCode(CUSTOMER_POST_CODE);
        return addressDto;
    }

    protected ContactDataDto createContacts() {
        ContactDataDto contactDataDto = new ContactDataDto();
        contactDataDto.setId(RANDOM_VALUE);
        contactDataDto.setPhone(CUSTOMER_PHONE);
        contactDataDto.setPhoneType(CUSTOMER_PHONE_TYPE);
        return contactDataDto;
    }
}
