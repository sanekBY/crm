package by.shalukho.service;

import by.shalukho.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceWithUniqueName<B extends AbstractEntity> {

    Optional<B> findByName(String name);
}
