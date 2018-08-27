package by.shalukho.entity.order;

import by.shalukho.entity.AbstractEntity;
import by.shalukho.enums.OrderStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "CUSTOMER_ORDER")
@EqualsAndHashCode(callSuper = true)
public class CustomerOrderEntity extends AbstractEntity {

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "FINAL_PRICE", nullable = false)
    private BigDecimal finalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private OrderStatusEnum status;
}
