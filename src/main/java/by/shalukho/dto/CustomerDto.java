package by.shalukho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends AbstractDto {
    private String email;
    private String name;
    private String type;

    private List<AddressDto> addresses;

    private List<ContactDataDto> contacts;

    public List<ContactDataDto> getContacts() {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        return contacts;
    }

    public List<AddressDto> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        return addresses;
    }


}
