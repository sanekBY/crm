package by.shalukho.repository;

import by.shalukho.entity.SiteSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteSectionRepository extends JpaRepository<SiteSectionEntity, Long> {
    Optional<SiteSectionEntity> findByActiveAndId(boolean active, Long id);

    List<SiteSectionEntity> findAllByActive(boolean active);

    List<SiteSectionEntity> findAllByActiveIsTrueAndParentSectionIsNull();

}
