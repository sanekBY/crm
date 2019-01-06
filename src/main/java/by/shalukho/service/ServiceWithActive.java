package by.shalukho.service;

import by.shalukho.entity.AbstractEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceWithActive<B extends AbstractEntity> {

    Optional<B> findByActiveIsTrueAndId(Long id);

    List<B> findAllByActiveIsTrue();

    Page<B> findAllByActiveIsTrue(@NonNull final Pageable pageable);
}
