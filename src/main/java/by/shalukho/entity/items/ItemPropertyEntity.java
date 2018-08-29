package by.shalukho.entity.items;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.item.ItemPropertyDto;
import by.shalukho.entity.AbstractNamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "ITEM_PROPERTY")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = ItemPropertyDto.class)
public class ItemPropertyEntity extends AbstractNamedEntity {

    @ManyToMany(mappedBy = "itemProperties", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ItemEntity> items;

    @ManyToMany(mappedBy = "itemProperties", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ItemTypeEntity> itemTypes;

}
