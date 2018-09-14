package by.shalukho.entity;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.OrderItemDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
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
@ConnectedDto(value = OrderItemDto.class)
public class OrderItemEntity extends AbstractItemEntity {

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ORDER_ID", nullable = false)
    @ToString.Exclude
    private CustomerOrderEntity order;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ORDER_ITEM_ITEM_PROPERTY", joinColumns = {@JoinColumn(name = "ORDER_ITEM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ITEM_PROPERTY_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderItemPropertyEntity> orderItemProperties;

}

