package by.shalukho.repository;

import by.shalukho.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByActiveAndId(boolean active, Long id);

    List<ItemEntity> findAllByActive(boolean active);
}
