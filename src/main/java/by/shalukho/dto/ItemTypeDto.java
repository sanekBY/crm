package by.shalukho.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ItemTypeDto {
    private Long id;
    private String name;
    private Set<ItemDto> items;
    private Set<ItemTypePropertyDto> itemTypeProperties;
}
