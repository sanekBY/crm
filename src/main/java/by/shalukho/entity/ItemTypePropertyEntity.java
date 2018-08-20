package by.shalukho.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "ITEM_TYPE_PROPERTY")
public class ItemTypePropertyEntity extends AbstractNamedEntity {

    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToMany(mappedBy = "itemTypeProperties")
    private Set<ItemTypeEntity> itemTypes;

}
