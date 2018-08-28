package by.shalukho.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String email;
    private String name;
    private String type;
    private List<AddressDto> addresses;
    private List<ContactDataDto> contacts;
}
