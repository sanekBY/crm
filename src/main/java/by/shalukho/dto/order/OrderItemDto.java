package by.shalukho.dto.order;

import by.shalukho.dto.AbstractDto;
import by.shalukho.dto.item.ItemPropertyDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemDto extends AbstractDto {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private List<ItemPropertyDto> orderItemProperties;
}
