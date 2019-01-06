package by.shalukho.repository;

import by.shalukho.entity.SiteReviewEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteReviewRepository extends JpaRepository<SiteReviewEntity, Long> {
    Optional<SiteReviewEntity> findByActiveIsTrueAndId(Long id);

    List<SiteReviewEntity> findAllByActiveIsTrue();

    Page<SiteReviewEntity> findAllByActiveIsTrue(@NonNull final Pageable page);
}
