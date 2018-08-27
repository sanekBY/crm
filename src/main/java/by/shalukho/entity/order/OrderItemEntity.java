package by.shalukho.entity.order;

import by.shalukho.entity.items.AbstractItemEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
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
@Table(name = "ORDER_ITEM")
@EqualsAndHashCode(callSuper = true)
public class OrderItemEntity extends AbstractItemEntity {

    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ORDER_ID", nullable = false)
    private CustomerOrderEntity order;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_ITEM_ITEM_PROPERTY", joinColumns = {@JoinColumn(name = "ORDER_ITEM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ITEM_PROPERTY_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderItemPropertyEntity> orderItemProperties;

}
