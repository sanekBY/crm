package by.shalukho.dto.order;

import by.shalukho.dto.item.ItemPropertyDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private List<ItemPropertyDto> orderItemProperties;
}
