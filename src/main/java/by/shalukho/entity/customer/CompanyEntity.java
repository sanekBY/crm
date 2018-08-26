package by.shalukho.entity.customer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "COMPANY")
public class CompanyEntity extends AbstractCustomerEntity {
}
