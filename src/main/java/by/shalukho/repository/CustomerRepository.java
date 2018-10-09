package by.shalukho.repository;

import by.shalukho.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByActiveIsTrueAndId(Long id);

    List<CustomerEntity> findAllByActiveIsTrue();

    Optional<CustomerEntity> findByName(String name);
}
