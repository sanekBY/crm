package by.shalukho.repository;

import by.shalukho.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    public ItemEntity findByActiveAndId(boolean active, Long id);

    public List<ItemEntity> findAllByActive(boolean active);
}
