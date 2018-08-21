package by.shalukho.repository;

import by.shalukho.entity.ItemTypePropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemTypePropertyRepository extends JpaRepository<ItemTypePropertyEntity, Long> {
    public ItemTypePropertyEntity findByActiveAndId(boolean active, Long id);

    public List<ItemTypePropertyEntity> findAllByActive(boolean active);
}
