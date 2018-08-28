package by.shalukho.entity.customer;

import by.shalukho.entity.AbstractNamedEntity;
import by.shalukho.enums.CustomerTypeEnum;
import by.shalukho.enums.PhoneTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "CUSTOMER")
@EqualsAndHashCode(callSuper = true)
public abstract class CustomerEntity extends AbstractNamedEntity {

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "PHONE_TYPE")
    private PhoneTypeEnum phoneType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private CustomerTypeEnum customerType;

}
