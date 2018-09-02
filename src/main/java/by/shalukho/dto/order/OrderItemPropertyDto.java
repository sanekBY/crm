package by.shalukho.dto.order;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItemPropertyDto extends AbstractDto {
    private String name;
}
