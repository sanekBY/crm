package by.shalukho.repository;

import by.shalukho.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByActiveAndId(boolean active, Long id);

    List<CustomerEntity> findAllByActive(boolean active);
}
