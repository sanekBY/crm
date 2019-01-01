package by.shalukho.repository;

import by.shalukho.entity.CustomerOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {

    Page<CustomerOrderEntity> findAll(Pageable pageable);

    Optional<CustomerOrderEntity> findByActiveIsTrueAndId(Long id);

    List<CustomerOrderEntity> findAllByActiveIsTrue();
}
