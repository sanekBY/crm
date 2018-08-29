package by.shalukho.entity.items;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.item.ItemTypeDto;
import by.shalukho.entity.AbstractNamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = ItemTypeDto.class)
public class ItemTypeEntity extends AbstractNamedEntity {

    @OneToMany(mappedBy = "itemType")
    private List<ItemEntity> items;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ITEM_TYPE_ITEM_PROPERTY", joinColumns = {@JoinColumn(name = "ITEM_TYPE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_PROPERTY_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ItemPropertyEntity> itemProperties;

}
