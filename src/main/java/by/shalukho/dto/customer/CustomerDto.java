package by.shalukho.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String phoneType;
    private String type;
}
