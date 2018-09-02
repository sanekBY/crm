package by.shalukho.dto.item;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemPropertyDto extends AbstractDto {
    private String name;
}
