package by.shalukho.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerOrderDto {
    private Long id;
    private String description;
    private BigDecimal finalPrice;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private List<OrderItemDto> orderItems;
}
