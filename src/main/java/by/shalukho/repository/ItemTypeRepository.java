package by.shalukho.repository;

import by.shalukho.entity.ItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemTypeRepository extends JpaRepository<ItemTypeEntity, Long> {
    public ItemTypeEntity findByActiveAndId(boolean active, Long id);

    public List<ItemTypeEntity> findAllByActive(boolean active);
}
