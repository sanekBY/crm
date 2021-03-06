package by.shalukho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SiteReviewDto extends AbstractDto {
    private String name;
    private String position;
    private String text;
    private boolean accepted;
}
