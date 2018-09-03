package by.shalukho.entity.order;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.order.OrderItemPropertyDto;
import by.shalukho.entity.items.AbstractItemPropertyEntity;
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
@Table(name = "ORDER_ITEM_PROPERTY")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = OrderItemPropertyDto.class)
public class OrderItemPropertyEntity extends AbstractItemPropertyEntity {

    @ManyToMany(mappedBy = "orderItemProperties", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderItemEntity> orderItems;

}
