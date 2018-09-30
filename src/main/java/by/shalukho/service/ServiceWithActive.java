package by.shalukho.service;

import by.shalukho.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceWithActive<B extends AbstractEntity> {

    Optional<B> findByActiveIsTrueAndId(Long id);

    List<B> findAllByActiveIsTrue();
}
