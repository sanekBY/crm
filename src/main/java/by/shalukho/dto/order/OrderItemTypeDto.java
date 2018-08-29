package by.shalukho.dto.order;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemTypeDto extends AbstractDto {
    private String name;
    private List<OrderItemPropertyDto> orderItemProperties;
}
