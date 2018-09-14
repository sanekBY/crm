package by.shalukho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContactDataDto extends AbstractDto {
    private String phone;
    private String phoneType;
}
