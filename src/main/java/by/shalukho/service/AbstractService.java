package by.shalukho.service;

import by.shalukho.converter.GenericConverter;
import by.shalukho.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class AbstractService<T, B extends AbstractEntity> implements ServiceWithActive<B> {
    private JpaRepository repository;

    private GenericConverter<T, B> converter;
    private Class<T> dtoClazz;
    private Class<B> dboClazz;

    @Transactional
    public T findById(Long id) {
        B entity = findByActiveAndId(true, id);
        return converter.convertToDto(entity);
    }

    public List<T> findAll() {
        return findAllByActive(true).stream().map(e -> converter.convertToDto((B) e)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        B entity = (B) repository.findById(id).get();
        entity.setActive(false);
        repository.save(entity);
    }

    public void save(T dto) {
        B entity = converter.convertToEntity(dto);
        repository.save(entity);
    }

    public List<T> findAllById(List<Long> ids) {
        List<B> entities = repository.findAllById(ids);
        List<T> dtos = (List<T>) entities.stream().map(e -> converter.convertToDto(e));
        return dtos;
    }

    public JpaRepository getRepository() {
        return repository;
    }

    public void setRepository(JpaRepository repository) {
        this.repository = repository;
    }

    public GenericConverter<T, B> getConverter() {
        return converter;
    }
}
