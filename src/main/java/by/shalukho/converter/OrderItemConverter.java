package by.shalukho.converter;

import by.shalukho.dto.OrderItemDto;
import by.shalukho.entity.OrderItemEntity;
import by.shalukho.entity.OrderItemPropertyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemConverter extends GenericConverter<OrderItemDto, OrderItemEntity> {

    @Autowired
    private OrderItemPropertyConverter orderItemPropertyConverter;

    public OrderItemConverter() {
        super(OrderItemEntity.class);
    }

    @Override
    protected OrderItemEntity extraConvertToEntity(final OrderItemDto orderItemDto,
                                                   final OrderItemEntity orderItemEntity) {
        final OrderItemEntity orderItem = super.extraConvertToEntity(orderItemDto, orderItemEntity);
        final List<OrderItemPropertyEntity> orderItemProperties =
                orderItemPropertyConverter.convertAllToEntity(orderItemDto.getOrderItemProperties());
        orderItem.setOrderItemProperties(orderItemProperties);

        return orderItem;
    }
}
