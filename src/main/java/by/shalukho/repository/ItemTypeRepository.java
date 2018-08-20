package by.shalukho.repository;

import by.shalukho.entity.ItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTypeRepository extends JpaRepository<ItemTypeEntity, Long> {
}
