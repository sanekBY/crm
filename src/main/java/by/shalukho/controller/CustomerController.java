package by.shalukho.controller;

import by.shalukho.dto.AddressDto;
import by.shalukho.dto.ContactDataDto;
import by.shalukho.dto.CustomerDto;
import by.shalukho.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/customer")
@PreAuthorize("hasAuthority('ADMIN')")
public class CustomerController extends AbstractController<CustomerDto, CustomerService> {

    public static final String CUSTOMER_DTO_ATTRIBUTE = "customerDto";

    @Autowired
    public CustomerController(CustomerService customerService) {
        super(customerService, CustomerDto.class);
    }

    @RequestMapping(params = {"addPhone"})
    public String addPhone(final CustomerDto customerDto) {
        customerDto.getContacts().add(new ContactDataDto());
        return getHtml();
    }

    @RequestMapping(params = {"removePhone"}, method = RequestMethod.POST)
    public String removePhone(final CustomerDto customerDto, final HttpServletRequest req) {
        final Integer phoneId = Integer.valueOf(req.getParameter("removePhone"));
        customerDto.getContacts().remove(phoneId.intValue());
        return getHtml();
    }

    @RequestMapping(params = {"addAddress"})
    public String addAddress(final CustomerDto customerDto) {
        customerDto.getAddresses().add(new AddressDto());
        return getHtml();
    }

    @RequestMapping(params = {"removeAddress"}, method = RequestMethod.POST)
    public String removeAddress(final CustomerDto customerDto, final HttpServletRequest req) {
        final Integer addressId = Integer.valueOf(req.getParameter("removeAddress"));
        customerDto.getAddresses().remove(addressId.intValue());
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
