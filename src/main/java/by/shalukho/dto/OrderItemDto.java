package by.shalukho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItemDto extends AbstractDto {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private List<OrderItemPropertyDto> orderItemProperties;
}
