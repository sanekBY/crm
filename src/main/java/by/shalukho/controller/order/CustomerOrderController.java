package by.shalukho.controller.order;

import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.service.order.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('ADMIN')")
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderController(final CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @RequestMapping(value = "/api/customer-order", method = RequestMethod.POST)
    public String createCustomerOrder(@RequestBody final CustomerOrderDto customerOrderDto) {
        customerOrderService.save(customerOrderDto);
        return "Customer order is created";
    }

    @RequestMapping(value = "/api/customer-order/{id}", method = RequestMethod.DELETE)
    public String deleteCustomerOrder(@PathVariable("id") final Long id) {
        customerOrderService.delete(id);
        return "Customer order is deleted";
    }

    @RequestMapping(value = "/api/customer-order/{id}", method = RequestMethod.GET)
    public CustomerOrderDto getCustomerOrder(@PathVariable("id") final Long id) {
        return customerOrderService.findById(id);
    }

    @RequestMapping(value = "/api/customer-order/{id}", method = RequestMethod.PUT)
    public String updateCustomerOrder(@RequestBody final CustomerOrderDto customerOrderDto) {
        customerOrderDto.setModifiedOn(LocalDateTime.now());
        customerOrderService.save(customerOrderDto);
        return "Customer order is saved";
    }

    @RequestMapping(value = "/api/customer-order", method = RequestMethod.GET)
    public List<CustomerOrderDto> getCustomerOrders() {
        return customerOrderService.findAll();
    }

}
