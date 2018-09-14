package by.shalukho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddressDto extends AbstractDto {
    private String city;
    private String state;
    private String address;
    private String postalCode;
}
