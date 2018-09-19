package by.shalukho.controller;

import by.shalukho.dto.ContactDataDto;
import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.CustomerEntity;
import by.shalukho.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping(value = "/customer")
@PreAuthorize("hasAuthority('ADMIN')")
public class CustomerController extends AbstractController<CustomerDto, CustomerEntity> {


    public static final String CUSTOMER_DTO_ATTRIBUTE = "customerDto";

    @Autowired
    public CustomerController(CustomerService customerService) {
        super(customerService, CustomerDto.class);
    }

    @Override
    protected CustomerDto createNewObject() throws InstantiationException, IllegalAccessException {
        CustomerDto newObject = super.createNewObject();
        newObject.setContacts(Arrays.asList(new ContactDataDto()));
        return newObject;
    }

    @RequestMapping(params = {"addRow"})
    public String addRow(final CustomerDto customerDto, final BindingResult bindingResult) {
        customerDto.getContacts().add(new ContactDataDto());
        return getHtml();
    }

    @Override
    protected String getAttribute() {
        return CUSTOMER_DTO_ATTRIBUTE;
    }

    @Override
    protected String getListAttribute() {
        return "customers";
    }

    @Override protected String getListHtml() {
        return "/customer/customers";
    }

    @Override protected String getHtml() {
        return "/customer/customer";
    }

}
