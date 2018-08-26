package by.shalukho.repository;

import by.shalukho.entity.items.ItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemTypeRepository extends JpaRepository<ItemTypeEntity, Long> {
    public Optional<ItemTypeEntity> findByActiveAndId(boolean active, Long id);

    public List<ItemTypeEntity> findAllByActive(boolean active);
}
