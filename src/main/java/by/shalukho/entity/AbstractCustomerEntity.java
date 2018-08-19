package by.shalukho.entity;

import by.shalukho.enums.PhoneTypeEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AbstractCustomerEntity extends AbstractNamedEntity {

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "PHONE_TYPE")
    private PhoneTypeEnum phoneType;

}
