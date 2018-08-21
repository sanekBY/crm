package by.shalukho.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ItemTypePropertyDto {
    private Long id;
    private String name;
    private Set<ItemTypeDto> itemTypes;
}
