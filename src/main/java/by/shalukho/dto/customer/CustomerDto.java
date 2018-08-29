package by.shalukho.dto.customer;

import by.shalukho.dto.AbstractDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDto extends AbstractDto {
    private String email;
    private String name;
    private String type;
    private List<AddressDto> addresses;
    private List<ContactDataDto> contacts;
}
