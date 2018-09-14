package by.shalukho.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class AbstractItemPropertyEntity extends AbstractNamedEntity {

}
