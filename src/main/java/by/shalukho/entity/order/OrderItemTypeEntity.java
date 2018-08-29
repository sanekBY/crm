package by.shalukho.entity.order;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.order.OrderItemPropertyDto;
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
@Table(name = "ORDER_ITEM_TYPE")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = OrderItemPropertyDto.class)
public class OrderItemTypeEntity extends AbstractNamedEntity {

    @OneToMany(mappedBy = "orderItemType")
    private List<OrderItemEntity> orderItems;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_ITEM_TYPE_ITEM_PROPERTY", joinColumns = {@JoinColumn(name = "ORDER_ITEM_TYPE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ITEM_PROPERTY_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderItemPropertyEntity> orderItemProperties;

}
