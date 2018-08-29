package by.shalukho.dto.customer;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto extends AbstractDto {
    private String city;
    private String state;
    private String address;
    private String postalCode;
}
