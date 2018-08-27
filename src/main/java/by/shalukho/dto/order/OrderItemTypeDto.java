package by.shalukho.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemTypeDto {
    private Long id;
    private String name;
    private List<OrderItemPropertyDto> orderItemProperties;
}
