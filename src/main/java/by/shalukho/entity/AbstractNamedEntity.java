package by.shalukho.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractEntity {

    @Column(name = "NAME")
    private String name;
}
