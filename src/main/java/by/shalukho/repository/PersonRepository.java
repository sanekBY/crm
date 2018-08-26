package by.shalukho.repository;

import by.shalukho.entity.customer.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    public Optional<PersonEntity> findByActiveAndId(boolean active, Long id);

    public List<PersonEntity> findAllByActive(boolean active);
}
