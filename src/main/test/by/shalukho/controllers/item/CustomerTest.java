package by.shalukho.controllers.item;

import by.shalukho.dto.customer.AddressDto;
import by.shalukho.dto.customer.ContactDataDto;
import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.enums.PhoneTypeEnum;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerTest extends AbstractTest {

    private static final String API_CUSTOMER_WITHOUT_ID = "/api/customer";

    @Test
    public void checkCustomerCreation() throws Exception {

        CustomerDto customerDto = createCustomer();

        createPostRequest(API_CUSTOMER_WITHOUT_ID, this.json(customerDto));

        getMockMvc().perform(get(API_CUSTOMER_WITHOUT_ID + "/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(customerDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(customerDto.getName())))
                .andExpect(jsonPath("$.email", is(customerDto.getEmail())))
                .andExpect(jsonPath("$.type", is(customerDto.getType())));
    }

    @Test
    public void checkCustomerWithDataCreation() throws Exception {

        CustomerDto customerDto = createCustomer();
        customerDto.setAddresses(Arrays.asList(createAddress()));
        customerDto.setContacts(Arrays.asList(createContacts()));

        createPostRequest(API_CUSTOMER_WITHOUT_ID, this.json(customerDto));

        getMockMvc().perform(get(API_CUSTOMER_WITHOUT_ID + "/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(customerDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(customerDto.getName())))
                .andExpect(jsonPath("$.email", is(customerDto.getEmail())))
                .andExpect(jsonPath("$.type", is(customerDto.getType())));
    }

    private CustomerDto createCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(ID);
        customerDto.setName("Ivan Ivanov");
        customerDto.setEmail("customer@gmail.com");
        customerDto.setType("PERSON");
        return customerDto;
    }

    private AddressDto createAddress() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(ID);
        addressDto.setAddress("Nezalezhnasti pr.");
        addressDto.setCity("Minsk");
        addressDto.setState("Minsk vobl.");
        addressDto.setPostalCode("220056");
        return addressDto;
    }

    private ContactDataDto createContacts() {
        ContactDataDto contactDataDto = new ContactDataDto();
        contactDataDto.setPhone("+375291820620");
        contactDataDto.setPhoneType(PhoneTypeEnum.MOBILE.toString());
        return contactDataDto;
    }


}
