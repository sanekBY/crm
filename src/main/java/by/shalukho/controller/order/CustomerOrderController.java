package by.shalukho.controller.order;

import by.shalukho.controller.AbstractController;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.entity.order.CustomerOrderEntity;
import by.shalukho.service.order.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/order")
//@PreAuthorize("hasAuthority('ADMIN')")
public class CustomerOrderController extends AbstractController<CustomerOrderDto, CustomerOrderEntity> {

    public static final String CUSTOMER_ORDER_DTO_ATTRIBUTE = "customerOrderDto";

    @Autowired
    public CustomerOrderController(final CustomerOrderService customerOrderService) {
        super(customerOrderService, CustomerOrderDto.class);
    }

    @Override protected String getAttribute() {
        return CUSTOMER_ORDER_DTO_ATTRIBUTE;
    }

    @Override protected String getListAttribute() {
        return "customerOrders";
    }

    @Override protected String getListHtml() {
        return "/order/order";
    }

    @Override protected String getHtml() {
        return "/order/orders";
    }
}
