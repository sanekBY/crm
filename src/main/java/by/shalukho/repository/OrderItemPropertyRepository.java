package by.shalukho.repository;

import by.shalukho.entity.order.OrderItemPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemPropertyRepository extends JpaRepository<OrderItemPropertyEntity, Long> {
    public Optional<OrderItemPropertyEntity> findByActiveAndId(boolean active, Long id);

    public List<OrderItemPropertyEntity> findAllByActive(boolean active);
}
