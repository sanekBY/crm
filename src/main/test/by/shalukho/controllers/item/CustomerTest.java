package by.shalukho.controllers.item;

import by.shalukho.dto.customer.AddressDto;
import by.shalukho.dto.customer.ContactDataDto;
import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.enums.CustomerTypeEnum;
import by.shalukho.enums.PhoneTypeEnum;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;

public class CustomerTest extends AbstractTest {

    private static final String API_CUSTOMER_WITHOUT_ID = "/api/customer";
    private static final String CUSTOMER_NAME = "Ivan Ivanov";
    private static final String CUSTOMER_EMAIL = "customer@gmail.com";
    private static final String CUSTOMER_TYPE = CustomerTypeEnum.COMPANY.toString();
    private static final String CUSOTMER_ADDRESS = "Nezalezhnasti pr.";
    private static final String CUSTOMER_CITY = "Minsk";
    private static final String CUSTOMER_STATE = "Minsk vobl.";
    private static final String CUSTOMER_POST_CODE = "220056";
    private static final String CUSTOMER_PHONE = "+375291820620";
    private static final String CUSTOMER_PHONE_TYPE = PhoneTypeEnum.MOBILE.toString();

    @Test
    public void checkCustomerCreation() throws Exception {

        CustomerDto customerDto = createCustomer();

        createPostRequest(API_CUSTOMER_WITHOUT_ID, this.json(customerDto));

        expectations.put("$.id", is(customerDto.getId().intValue()));
        expectations.put("$.name", is(customerDto.getName()));
        expectations.put("$.email", is(customerDto.getEmail()));
        expectations.put("$.type", is(customerDto.getType()));

        checkGetRequest(API_CUSTOMER_WITHOUT_ID + "/" + ID);
    }

    @Test
    public void checkCustomerWithDataCreation() throws Exception {

        final AddressDto addressDto = createAddress();
        final ContactDataDto contactDataDto = createContacts();

        CustomerDto customerDto = createCustomer();
        customerDto.setAddresses(Arrays.asList(addressDto));
        customerDto.setContacts(Arrays.asList(contactDataDto));

        createPostRequest(API_CUSTOMER_WITHOUT_ID, this.json(customerDto));

        expectations.put("$.id", is(customerDto.getId().intValue()));
        expectations.put("$.name", is(customerDto.getName()));
        expectations.put("$.email", is(customerDto.getEmail()));
        expectations.put("$.type", is(customerDto.getType()));
        expectations.put("$.addresses[0].id", is(addressDto.getId().intValue()));
        expectations.put("$.addresses[0].city", is(addressDto.getCity()));
        expectations.put("$.addresses[0].state", is(addressDto.getState()));
        expectations.put("$.addresses[0].address", is(addressDto.getAddress()));
        expectations.put("$.addresses[0].postalCode", is(addressDto.getPostalCode()));
        expectations.put("$.contacts[0].id", is(contactDataDto.getId().intValue()));
        expectations.put("$.contacts[0].phone", is(contactDataDto.getPhone()));
        expectations.put("$.contacts[0].phoneType", is(contactDataDto.getPhoneType()));

        checkGetRequest(API_CUSTOMER_WITHOUT_ID + "/" + ID);
    }

    private CustomerDto createCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(ID);
        customerDto.setName(CUSTOMER_NAME);
        customerDto.setEmail(CUSTOMER_EMAIL);
        customerDto.setType(CUSTOMER_TYPE);
        return customerDto;
    }

    private AddressDto createAddress() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(ID);
        addressDto.setAddress(CUSOTMER_ADDRESS);
        addressDto.setCity(CUSTOMER_CITY);
        addressDto.setState(CUSTOMER_STATE);
        addressDto.setPostalCode(CUSTOMER_POST_CODE);
        return addressDto;
    }

    private ContactDataDto createContacts() {
        ContactDataDto contactDataDto = new ContactDataDto();
        contactDataDto.setId(ID);
        contactDataDto.setPhone(CUSTOMER_PHONE);
        contactDataDto.setPhoneType(CUSTOMER_PHONE_TYPE);
        return contactDataDto;
    }


}
