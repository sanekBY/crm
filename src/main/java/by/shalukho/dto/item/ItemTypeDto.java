package by.shalukho.dto.item;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemTypeDto {
    private Long id;
    private String name;
    private List<ItemTypePropertyDto> itemTypeProperties;
}
