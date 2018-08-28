package by.shalukho.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDataDto {
    private Long id;
    private String phone;
    private String phoneType;
}
