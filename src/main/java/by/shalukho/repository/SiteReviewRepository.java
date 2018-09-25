package by.shalukho.repository;

import by.shalukho.entity.SiteReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteReviewRepository extends JpaRepository<SiteReviewEntity, Long> {
    Optional<SiteReviewEntity> findByActiveAndId(boolean active, Long id);

    List<SiteReviewEntity> findAllByActive(boolean active);
}
