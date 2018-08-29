package by.shalukho.controllers.item;

import by.shalukho.dto.customer.AddressDto;
import by.shalukho.dto.customer.ContactDataDto;
import by.shalukho.dto.customer.CustomerDto;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;

public class CustomerTest extends AbstractCustomerTest {

    @Test
    public void checkCustomerCreation() {

        CustomerDto customerDto = createCustomer();

        createPostRequest(API_CUSTOMER_WITHOUT_ID, customerDto);

        expectations.put("$.id", is(customerDto.getId().intValue()));
        expectations.put("$.name", is(customerDto.getName()));
        expectations.put("$.email", is(customerDto.getEmail()));
        expectations.put("$.type", is(customerDto.getType()));

        checkGetRequest(API_CUSTOMER_WITHOUT_ID + "/" + ID);
    }

    @Test
    public void checkCustomerWithDataCreation() {

        final AddressDto addressDto = createAddress();
        final ContactDataDto contactDataDto = createContacts();

        CustomerDto customerDto = createCustomer();
        customerDto.setAddresses(Arrays.asList(addressDto));
        customerDto.setContacts(Arrays.asList(contactDataDto));

        createPostRequest(API_CUSTOMER_WITHOUT_ID, customerDto);

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

}
