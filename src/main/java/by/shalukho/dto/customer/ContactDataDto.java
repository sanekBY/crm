package by.shalukho.dto.customer;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDataDto extends AbstractDto {
    private String phone;
    private String phoneType;
}
