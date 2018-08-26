package by.shalukho.entity.items;

import by.shalukho.entity.AbstractNamedEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "ITEM_TYPE_PROPERTY")
public class ItemTypePropertyEntity extends AbstractNamedEntity {

    @ManyToMany(mappedBy = "itemTypeProperties")
    private List<ItemTypeEntity> itemTypes;

}
