package by.shalukho.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "ITEM_TYPE")
public class ItemType extends AbstractNamedEntity {

    @OneToMany(mappedBy = "itemType")
    private List<Item> items;

    @ManyToOne
    @JoinTable(name = "ITEM_TYPE_ITEM_TYPE_PROPERTY", joinColumns = {@JoinColumn(name = "ITEM_TYPE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_TYPE_PROPERTY_ID")})
    private Set<ItemTypeProperty> itemTypeProperties;
}
