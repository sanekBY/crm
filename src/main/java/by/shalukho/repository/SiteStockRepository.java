package by.shalukho.repository;

import by.shalukho.entity.SiteStockEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteStockRepository extends JpaRepository<SiteStockEntity, Long> {
    Optional<SiteStockEntity> findByActiveIsTrueAndId(Long id);

    List<SiteStockEntity> findAllByActiveIsTrue();

    Page<SiteStockEntity> findAllByActiveIsTrue(@NonNull final Pageable page);
}
