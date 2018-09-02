package by.shalukho.dto.item;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemTypeDto extends AbstractDto {
    private String name;
    private List<ItemPropertyDto> itemProperties;
}
