package by.shalukho.service;

import by.shalukho.entity.AbstractEntity;

import java.util.List;

public interface ServiceWithActive<B extends AbstractEntity> {

    public B findByActiveAndId(boolean active, Long id);

    public List<B> findAllByActive(boolean active);
}
