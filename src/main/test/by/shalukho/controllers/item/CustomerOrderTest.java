package by.shalukho.controllers.item;

import by.shalukho.dto.customer.CustomerDto;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.dto.order.OrderItemDto;
import by.shalukho.enums.OrderStatusEnum;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;

public class CustomerOrderTest extends AbstractCustomerTest {

    public static final String API_CUSTOMER_ORDER_WITHOU_ID = "/api/customer-order";
    public static final String CUSTOMER_ORDER_DESCRIPTION = "Deliver order up to 12 AM";
    public static final String ORDER_ITEM_NAME = "Business cards";
    public static final String ORDER_ITEM_DESCRIPTION = "Item";

    @Test
    public void testCheckOrderCreation() {
        final CustomerDto customer = createCustomer();
        final OrderItemDto orderItem = createOrderItem();

        final CustomerOrderDto customerOrderDto = createCustomerOrderDto(customer, orderItem);

        createPostRequest(API_CUSTOMER_WITHOUT_ID, customer);
        createPostRequest(API_CUSTOMER_ORDER_WITHOU_ID, customerOrderDto);

        expectations.put("$.id", is(customerOrderDto.getId().intValue()));
        expectations.put("$.description", is(customerOrderDto.getDescription()));
        expectations.put("$.status", is(customerOrderDto.getStatus()));
        expectations.put("$.finalPrice", is(customerOrderDto.getFinalPrice().intValue()));
        expectations.put("$.customer.email", is(customer.getEmail()));
        expectations.put("$.customer.name", is(customer.getName()));
        expectations.put("$.customer.type", is(customer.getType()));
        expectations.put("$.orderItems[0].id", is(1));
        expectations.put("$.orderItems[0].name", is(orderItem.getName()));
        expectations.put("$.orderItems[0].description", is(orderItem.getDescription()));
        expectations.put("$.orderItems[0].price", is(orderItem.getPrice().intValue()));
        expectations.put("$.orderItems[0].quantity", is(orderItem.getQuantity()));

        checkGetRequest(API_CUSTOMER_ORDER_WITHOU_ID + "/" + ID);
    }

    private CustomerOrderDto createCustomerOrderDto(final CustomerDto customer,
                                                    final OrderItemDto orderItem) {

        final CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        customerOrderDto.setId(ID);
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
        return orderItem;
    }

}
