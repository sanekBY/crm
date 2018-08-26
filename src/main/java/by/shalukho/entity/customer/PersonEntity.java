package by.shalukho.entity.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PERSON")
@EqualsAndHashCode(callSuper = true)
public class PersonEntity extends AbstractCustomerEntity {
}
