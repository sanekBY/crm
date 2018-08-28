package by.shalukho.controller.customer;

import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public String createcustomer(@RequestBody final CustomerDto customerDto) {
        customerService.save(customerDto);
        return "customerEntity created";
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.DELETE)
    public String deletecustomer(@PathVariable("id") final Long id) {
        customerService.delete(id);
        return "customerEntity deleted";
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public CustomerDto getcustomer(@PathVariable("id") final Long id) {
        return customerService.findById(id);
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    public List<CustomerDto> getcustomers() {
        return customerService.findAll();
    }

}
