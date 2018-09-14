package by.shalukho.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class AbstractItemEntity extends AbstractNamedEntity {

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "DESCRIPTION")
    private String description;

}
