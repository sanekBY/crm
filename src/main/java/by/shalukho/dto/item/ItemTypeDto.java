package by.shalukho.dto.item;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemTypeDto extends AbstractDto {
    private String name;
    private List<ItemPropertyDto> itemProperties;
}
