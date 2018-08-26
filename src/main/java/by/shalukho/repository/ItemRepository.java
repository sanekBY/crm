package by.shalukho.repository;

import by.shalukho.entity.items.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    public Optional<ItemEntity> findByActiveAndId(boolean active, Long id);

    public List<ItemEntity> findAllByActive(boolean active);
}
