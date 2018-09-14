package by.shalukho.entity;

import by.shalukho.dto.AddressDto;
import by.shalukho.dto.ConnectedDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CUSTOMER_ADDRESS")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = AddressDto.class)
public class AddressEntity extends AbstractEntity {

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

}
