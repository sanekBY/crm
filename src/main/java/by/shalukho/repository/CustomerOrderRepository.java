package by.shalukho.repository;

import by.shalukho.entity.order.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {
    public Optional<CustomerOrderEntity> findByActiveAndId(boolean active, Long id);

    public List<CustomerOrderEntity> findAllByActive(boolean active);
}
