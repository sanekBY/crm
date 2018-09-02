package by.shalukho.controllers.item;

import by.shalukho.dto.customer.AddressDto;
import by.shalukho.dto.customer.ContactDataDto;
import by.shalukho.dto.customer.CustomerDto;
import org.junit.Test;

import java.util.Arrays;

import static by.shalukho.controller.customer.CustomerController.CUSTOMER_DTO_ATTRIBUTE;

public class CustomerTest extends AbstractCustomerTest {

    @Test
    public void checkCustomerCreation() {

        CustomerDto customerDto = createCustomer();

        checkEntityCreation(API_CUSTOMER_WITHOUT_ID, CUSTOMER_DTO_ATTRIBUTE, customerDto);
    }

    @Test
    public void checkCustomerWithDataCreation() {

        final AddressDto addressDto = createAddress();
        final ContactDataDto contactDataDto = createContacts();

        CustomerDto customerDto = createCustomer();
        customerDto.setAddresses(Arrays.asList(addressDto));
        customerDto.setContacts(Arrays.asList(contactDataDto));

        checkEntityCreation(API_CUSTOMER_WITHOUT_ID, CUSTOMER_DTO_ATTRIBUTE, customerDto);
    }

}
