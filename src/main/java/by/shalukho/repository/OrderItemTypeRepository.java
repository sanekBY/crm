package by.shalukho.repository;

import by.shalukho.entity.order.OrderItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemTypeRepository extends JpaRepository<OrderItemTypeEntity, Long> {
    public Optional<OrderItemTypeEntity> findByActiveAndId(boolean active, Long id);

    public List<OrderItemTypeEntity> findAllByActive(boolean active);
}
