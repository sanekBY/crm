package by.shalukho.repository;

import by.shalukho.entity.OrderItemPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemPropertyRepository extends JpaRepository<OrderItemPropertyEntity, Long> {
    Optional<OrderItemPropertyEntity> findByActiveIsTrueAndId(Long id);

    List<OrderItemPropertyEntity> findAllByActiveIsTrue();
}
