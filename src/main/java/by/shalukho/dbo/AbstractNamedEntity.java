package by.shalukho.dbo;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractEntity {
    @Basic
    @Column(name = "NAME")
    private String name;
}
