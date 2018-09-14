package by.shalukho.service;

import by.shalukho.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceWithActive<B extends AbstractEntity> {

    Optional<B> findByActiveAndId(boolean active, Long id);

    List<B> findAllByActive(boolean active);
}
