package by.shalukho.entity.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "COMPANY")
@EqualsAndHashCode(callSuper = true)
public class CompanyEntity extends AbstractCustomerEntity {
}
