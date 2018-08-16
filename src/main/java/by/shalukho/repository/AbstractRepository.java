package by.shalukho.repository;

import by.shalukho.dbo.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<Entity extends AbstractEntity, Long> extends JpaRepository<Entity, Long> {
    Entity findIsActive();
}
