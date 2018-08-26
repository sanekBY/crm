package by.shalukho.entity.items;

import by.shalukho.entity.AbstractNamedEntity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ITEM")
public class ItemEntity extends AbstractNamedEntity {

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ITEM_TYPE_ID", nullable=false)
    private ItemTypeEntity itemType;

}
