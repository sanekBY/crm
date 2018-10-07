package by.shalukho.service;

import by.shalukho.converter.AddressConverter;
import by.shalukho.converter.ContactDataConverter;
import by.shalukho.converter.CustomerConverter;
import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.CustomerEntity;
import by.shalukho.entity.CustomerTypeEnum;
import by.shalukho.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    @InjectMocks
    private CustomerConverter customerConverter = new CustomerConverter();

    @Spy
    private AddressConverter addressConverter;

    @Spy
    private ContactDataConverter contactDataConverter;

    private static final Long CUSTOMER_ID = 1L;
    public static final String CUSTOMER_NAME = "Item";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCustomerTest() {
        final CustomerEntity customerEntity = getCustomerEntity();

        Mockito.when(customerRepository.findByActiveIsTrueAndId(CUSTOMER_ID)).thenReturn(Optional.of(customerEntity));

        final CustomerDto customerDto = customerService.findById(CUSTOMER_ID);
        Assert.assertEquals(customerDto.getId(), customerEntity.getId());
        Assert.assertEquals(customerDto.getName(), customerEntity.getName());
        Assert.assertEquals(customerDto.getType(), customerEntity.getType().toString());
    }

    @Test
    public void saveCustomer() {
        customerService.save(new CustomerDto());
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void deleteCustomer() {
        final CustomerEntity customerEntity = getCustomerEntity();

        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customerEntity));

        customerService.delete(CUSTOMER_ID);

        Mockito.verify(customerRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any());
    }

    private CustomerEntity getCustomerEntity() {
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(CUSTOMER_ID);
        customerEntity.setName(CUSTOMER_NAME);
        customerEntity.setType(CustomerTypeEnum.COMPANY);
        return customerEntity;
    }

}
