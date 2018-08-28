package by.shalukho.repository;

import by.shalukho.entity.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    public Optional<CustomerEntity> findByActiveAndId(boolean active, Long id);

    public List<CustomerEntity> findAllByActive(boolean active);
}
