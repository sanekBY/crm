package by.shalukho.dto.order;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemPropertyDto extends AbstractDto {
    private String name;
}
