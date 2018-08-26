package by.shalukho.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "ITEM_TYPE")
public class ItemTypeEntity extends AbstractNamedEntity {

    @OneToMany(mappedBy = "itemType")
    private List<ItemEntity> items;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ITEM_TYPE_ITEM_TYPE_PROPERTY", joinColumns = {@JoinColumn(name = "ITEM_TYPE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_TYPE_PROPERTY_ID")})
    private List<ItemTypePropertyEntity> itemTypeProperties;
}
