package by.shalukho.dto.order;

import by.shalukho.dto.AbstractDto;
import by.shalukho.dto.customer.CustomerDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerOrderDto extends AbstractDto {
    private String description;
    private BigDecimal finalPrice;
    private CustomerDto customer;
    private String status;
    private List<OrderItemDto> orderItems;
}
