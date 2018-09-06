package by.shalukho.controllers.customer;

import by.shalukho.controller.customer.CustomerController;
import by.shalukho.controller.order.CustomerOrderController;
import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.dto.order.OrderItemDto;
import by.shalukho.enums.OrderStatusEnum;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class CustomerOrderTest extends AbstractCustomerTest {

    public static final String CUSTOMER_ORDER_WITHOU_ID_URL = "/order";
    public static final String CUSTOMER_ORDER_DESCRIPTION = "Deliver order up to 12 AM";
    public static final String ORDER_ITEM_NAME = "Business cards";
    public static final String ORDER_ITEM_DESCRIPTION = "Item";

    @Test
    public void testCheckOrderCreation() {
        final CustomerDto customerDto = createCustomer();
        final OrderItemDto orderItemDto = createOrderItem();

        final CustomerOrderDto customerOrderDto = createCustomerOrderDto(customerDto, orderItemDto);

        createPostRequest(API_CUSTOMER_WITHOUT_ID, CustomerController.CUSTOMER_DTO_ATTRIBUTE, customerDto);

        expectations.add(hasProperty("description", is(customerOrderDto.getDescription())));
        expectations.add(hasProperty("finalPrice", is(customerOrderDto.getFinalPrice())));
        expectations.add(hasProperty("status", is(customerOrderDto.getStatus())));
        expectations.add(hasProperty("customer", is(customerOrderDto.getCustomer())));
//        expectations.add(hasProperty("orderItems", is(customerOrderDto.getOrderItems())));

        checkEntityCreation(CUSTOMER_ORDER_WITHOU_ID_URL, CustomerOrderController.CUSTOMER_ORDER_DTO_ATTRIBUTE,
                            customerOrderDto);
    }

    private CustomerOrderDto createCustomerOrderDto(final CustomerDto customer,
                                                    final OrderItemDto orderItem) {

        final CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        customerOrderDto.setId(RANDOM_VALUE);
        customerOrderDto.setDescription(CUSTOMER_ORDER_DESCRIPTION);
        customerOrderDto.setCustomer(customer);
        customerOrderDto.setFinalPrice(new BigDecimal(2000));
        customerOrderDto.setOrderItems(Arrays.asList(orderItem));
        customerOrderDto.setStatus(OrderStatusEnum.OPENED.toString());
        return customerOrderDto;
    }


    private OrderItemDto createOrderItem() {
        OrderItemDto orderItem = new OrderItemDto();
        orderItem.setDescription(ORDER_ITEM_DESCRIPTION);
        orderItem.setName(ORDER_ITEM_NAME);
        orderItem.setPrice(new BigDecimal(1000));
        orderItem.setQuantity(2);
        orderItem.setOrderItemProperties(new ArrayList<>());
        return orderItem;
    }

}
