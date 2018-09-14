package by.shalukho.repository;

import by.shalukho.entity.ItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemTypeRepository extends JpaRepository<ItemTypeEntity, Long> {
    Optional<ItemTypeEntity> findByActiveAndId(boolean active, Long id);

    List<ItemTypeEntity> findAllByActive(boolean active);
}
