package by.shalukho.repository;

import by.shalukho.entity.CustomerEntity;
import by.shalukho.entity.ItemEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByActiveIsTrueAndId(Long id);

    List<CustomerEntity> findAllByActiveIsTrue();

    Optional<CustomerEntity> findByName(String name);

    Page<CustomerEntity> findAllByActiveIsTrue(@NonNull final Pageable page);

}
