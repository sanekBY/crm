package by.shalukho.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SiteCompanyContactsDto extends AbstractDto {
    private String city;
    private String street;
    private String house;
    private String buildingType;
    private String undergroundStation;
    private String room;
    private String phone;
    private String email;
    private String workTime;
}
