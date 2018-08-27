package by.shalukho.dto.order;

import by.shalukho.dto.item.ItemPropertyDto;
import by.shalukho.dto.item.ItemTypeDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerOrderDto {
    private Long id;
    private String description;
    private BigDecimal finalPrice;
    private List<OrderItemDto> orderItems;
}
