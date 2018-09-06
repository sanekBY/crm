package by.shalukho.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractNamedEntity extends AbstractEntity {

    @Column(name = "NAME")
    private String name;
}
