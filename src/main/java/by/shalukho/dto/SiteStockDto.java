package by.shalukho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SiteStockDto extends AbstractDto {
    private String name;
    private String text;
    private String dateFrom;
    private String dateTo;
    private String imageUrl;
    private boolean opened;
    private ImageDto image;
}
