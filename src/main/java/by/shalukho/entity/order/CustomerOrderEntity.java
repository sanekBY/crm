package by.shalukho.entity.order;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.order.CustomerOrderDto;
import by.shalukho.entity.AbstractEntity;
import by.shalukho.entity.customer.CustomerEntity;
import by.shalukho.enums.OrderStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "CUSTOMER_ORDER")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = CustomerOrderDto.class)
public class CustomerOrderEntity extends AbstractEntity {

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "FINAL_PRICE", nullable = false)
    private BigDecimal finalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private OrderStatusEnum status;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "MODIFIED_ON", nullable = false)
    private LocalDateTime modifiedOn;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customerEntity;
}
