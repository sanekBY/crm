package by.shalukho.criteria;

import by.shalukho.SpringBootWebApplication;
import by.shalukho.config.H2TestProfileJPAConfig;
import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.CustomerEntity;
import by.shalukho.entity.CustomerTypeEnum;
import by.shalukho.service.CustomerService;
import by.shalukho.specification.SpecificationFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class, H2TestProfileJPAConfig.class})
@Transactional
@ActiveProfiles("test")
public class SearchCriteriaTest {

    public static final String TEST_USER_NAME = "Test User";
    public static final String USER_EMAIL = "user@gmail.com";
    @Autowired
    private CustomerService customerService;

    @Before
    public void init() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName(TEST_USER_NAME);
        customerDto.setEmail(USER_EMAIL);
        customerDto.setType(CustomerTypeEnum.COMPANY.toString());
        customerDto.setAddresses(new ArrayList<>());
        customerDto.setContacts(new ArrayList<>());
        customerService.save(customerDto);
    }

    @Test
    public void testEqual() {
        final List<CustomerDto> results = customerService.findAll(SpecificationFilter.equal(CustomerEntity::getName,
                                                                                            TEST_USER_NAME));
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(TEST_USER_NAME, results.get(0).getName());

        final List<CustomerDto> wrongResults =
                customerService.findAll(SpecificationFilter.equal(CustomerEntity::getName,
                                                                  TEST_USER_NAME + "1"));
        Assert.assertEquals(0, wrongResults.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testEqualIgnoreCase() {
        final List<CustomerDto> results =
                customerService.findAll(SpecificationFilter.equalIgnoreCase(CustomerEntity::getName,
                                                                            TEST_USER_NAME.toLowerCase()));
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(TEST_USER_NAME, results.get(0).getName());
    }

    @Test
    public void testLike() {
        final List<CustomerDto> results = customerService.findAll(SpecificationFilter.like(CustomerEntity::getName,
                                                                                           "User"));
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(TEST_USER_NAME, results.get(0).getName());

        final List<CustomerDto> wrongResults = customerService.findAll(SpecificationFilter.like(CustomerEntity::getName,
                                                                                                "VASYA"));
        Assert.assertEquals(0, wrongResults.size());
    }


    @Test
    public void testAnd() {
        final List<CustomerDto> results =
                customerService.findAll(SpecificationFilter.and(SpecificationFilter.equal(CustomerEntity::getName,
                                                                                          TEST_USER_NAME),
                                                                SpecificationFilter.equal(CustomerEntity::getEmail,
                                                                                          USER_EMAIL)));
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(TEST_USER_NAME, results.get(0).getName());

        final List<CustomerDto> wrongResults =
                customerService.findAll(SpecificationFilter.and(SpecificationFilter.equal(CustomerEntity::getName,
                                                                                          TEST_USER_NAME),
                                                                SpecificationFilter.equal(CustomerEntity::getEmail,
                                                                                          USER_EMAIL + "ss")));
        Assert.assertEquals(0, wrongResults.size());
    }

}
