package by.shalukho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderRepository, Long> {
    public Optional<CustomerOrderRepository> findByActiveAndId(boolean active, Long id);

    public List<CustomerOrderRepository> findAllByActive(boolean active);
}
