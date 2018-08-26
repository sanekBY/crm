package by.shalukho.repository;

import by.shalukho.entity.items.ItemTypePropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemTypePropertyRepository extends JpaRepository<ItemTypePropertyEntity, Long> {
    public Optional<ItemTypePropertyEntity> findByActiveAndId(boolean active, Long id);

    public List<ItemTypePropertyEntity> findAllByActive(boolean active);
}
