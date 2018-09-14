package by.shalukho.entity;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.ItemDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "ITEM")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = ItemDto.class)
public class ItemEntity extends AbstractItemEntity {

    @ManyToOne
    @JoinColumn(name = "ITEM_TYPE_ID", nullable = false)
    private ItemTypeEntity itemType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ITEM_ITEM_PROPERTY", joinColumns = {@JoinColumn(name = "ITEM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_PROPERTY_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ItemPropertyEntity> itemProperties;

}
