package by.shalukho.repository;

import by.shalukho.entity.SiteSectionEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteSectionRepository extends JpaRepository<SiteSectionEntity, Long> {


    Optional<SiteSectionEntity> findByActiveIsTrueAndId(Long id);

    List<SiteSectionEntity> findAllByActiveIsTrue();

    List<SiteSectionEntity> findAllByActiveIsTrueAndParentSectionIsNull();

    Page<SiteSectionEntity> findAllByActiveIsTrue(@NonNull final Pageable page);
}
