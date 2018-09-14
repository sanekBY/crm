package by.shalukho.repository;

import by.shalukho.entity.ItemPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemPropertyRepository extends JpaRepository<ItemPropertyEntity, Long> {
    Optional<ItemPropertyEntity> findByActiveAndId(boolean active, Long id);

    List<ItemPropertyEntity> findAllByActive(boolean active);
}
