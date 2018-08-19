package by.shalukho.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PERSON")
public class PersonEntity extends AbstractCustomerEntity {
}
