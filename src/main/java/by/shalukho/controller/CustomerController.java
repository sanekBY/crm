package by.shalukho.controller;

import by.shalukho.dto.CustomerDto;
import by.shalukho.entity.CustomerEntity;
import by.shalukho.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
