package by.shalukho.repository;

import by.shalukho.entity.ImageEntity;
import by.shalukho.entity.ImageTypeEnum;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImageRepository extends AbstractNamedRepository<ImageEntity> {
    Page<ImageEntity> findAllByActiveIsTrueAndType(@NonNull final Pageable page,
                                                   @NonNull final ImageTypeEnum imageType);
}
