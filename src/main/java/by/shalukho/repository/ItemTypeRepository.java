package by.shalukho.repository;

import by.shalukho.entity.ItemTypeEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemTypeRepository extends JpaRepository<ItemTypeEntity, Long> {
    Optional<ItemTypeEntity> findByActiveIsTrueAndId(Long id);

    List<ItemTypeEntity> findAllByActiveIsTrue();

    Page<ItemTypeEntity> findAllByActiveIsTrue(@NonNull final Pageable page);
}
