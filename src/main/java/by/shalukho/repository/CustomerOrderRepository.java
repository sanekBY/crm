package by.shalukho.repository;

import by.shalukho.entity.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {
    Optional<CustomerOrderEntity> findByActiveAndId(boolean active, Long id);

    List<CustomerOrderEntity> findAllByActive(boolean active);
}
