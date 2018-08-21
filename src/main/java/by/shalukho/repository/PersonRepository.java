package by.shalukho.repository;

import by.shalukho.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    public PersonEntity findByActiveAndId(boolean active, Long id);

    public List<PersonEntity> findAllByActive(boolean active);
}
