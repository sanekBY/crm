package by.shalukho.controllers.item;

import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.dto.order.OrderItemDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class CustomerOrderTest extends AbstractCustomerTest {

    @Test
    public void testCheckOrderCreation() {
        final CustomerDto customer = createCustomer();
        final CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        final OrderItemDto orderItem = createOrderItem();

        customerOrderDto.setId(ID);
        customerOrderDto.setDescription("Deliver order up to 12 AM");
        customerOrderDto.setCustomer(customer);
        customerOrderDto.setFinalPrice(new BigDecimal(2000));
        customerOrderDto.setOrderItems(Arrays.asList(orderItem));

        createPostRequest(API_CUSTOMER_WITHOUT_ID, customer);
        createPostRequest("/api/customer-order", customerOrderDto);
    }


    private OrderItemDto createOrderItem() {
        OrderItemDto orderItem = new OrderItemDto();
        orderItem.setDescription("Item");
        orderItem.setName("Business cards");
        orderItem.setPrice(new BigDecimal(1000));
        orderItem.setQuantity(2);
        orderItem.setId(ID);
        return orderItem;
    }

}
