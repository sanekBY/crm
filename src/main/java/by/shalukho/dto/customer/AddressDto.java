package by.shalukho.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String city;
    private String state;
    private String address;
    private String postalCode;
}
