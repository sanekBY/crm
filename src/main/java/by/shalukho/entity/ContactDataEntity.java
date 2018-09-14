package by.shalukho.entity;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.ContactDataDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CUSTOMER_CONTACT_DATA")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = ContactDataDto.class)
public class ContactDataEntity extends AbstractEntity {
    @Column(name = "PHONE")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "PHONE_TYPE")
    private PhoneTypeEnum phoneType;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;
}
