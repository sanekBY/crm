package by.shalukho.repository;

import by.shalukho.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    Optional<OrderItemEntity> findByActiveIsTrueAndId(Long id);

    List<OrderItemEntity> findAllByActiveIsTrue();
}
