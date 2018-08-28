package by.shalukho.repository;

import by.shalukho.entity.items.ItemPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemPropertyRepository extends JpaRepository<ItemPropertyEntity, Long> {
    public Optional<ItemPropertyEntity> findByActiveAndId(boolean active, Long id);

    public List<ItemPropertyEntity> findAllByActive(boolean active);
}