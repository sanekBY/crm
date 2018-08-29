package by.shalukho.dto.item;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemDto extends AbstractDto {
    private String name;
    private String description;
    private BigDecimal price;
    private ItemTypeDto itemType;
    private List<ItemPropertyDto> itemProperties;
}
