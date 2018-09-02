package by.shalukho.controller.customer;

import by.shalukho.controller.AbstractController;
import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.entity.customer.CustomerEntity;
import by.shalukho.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/customer")
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
