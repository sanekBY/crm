package by.shalukho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SiteSectionDto extends AbstractDto {
    private String name;
    private String url;
    private SiteSectionDto parentSection;
    private List<SiteSectionDto> siteSections;
}
