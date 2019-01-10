package by.shalukho.repository;

import by.shalukho.entity.ImageEntity;
import by.shalukho.entity.ImageTypeEnum;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    Optional<ImageEntity> findByActiveIsTrueAndId(Long id);

    List<ImageEntity> findAllByActiveIsTrue();

    Page<ImageEntity> findAllByActiveIsTrueAndType(@NonNull final Pageable page,
                                                    @NonNull final ImageTypeEnum imageType);

    Optional<ImageEntity> findByName(String name);

    Page<ImageEntity> findAllByActiveIsTrue(@NonNull final Pageable page);
}
