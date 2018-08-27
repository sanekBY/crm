package by.shalukho.repository;

import by.shalukho.entity.order.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    public Optional<OrderItemEntity> findByActiveAndId(boolean active, Long id);

    public List<OrderItemEntity> findAllByActive(boolean active);
}
