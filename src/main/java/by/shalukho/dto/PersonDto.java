package by.shalukho.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String phoneType;
}
