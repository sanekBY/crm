package by.shalukho.dto.order;

import by.shalukho.dto.AbstractDto;
import by.shalukho.dto.customer.CustomerDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerOrderDto extends AbstractDto {
    private String description;
    private BigDecimal finalPrice;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private List<OrderItemDto> orderItems;
    private CustomerDto customer;
}
