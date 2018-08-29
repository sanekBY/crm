package by.shalukho.service;

import by.shalukho.converter.GenericConverter;
import by.shalukho.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class AbstractService<T, B extends AbstractEntity> implements ServiceWithActive<B> {
    private JpaRepository repository;

    private GenericConverter<T, B> converter;

    public T findById(Long id) {
        Optional<B> entity = findByActiveAndId(true, id);
        if (entity.isPresent()) {
            return converter.convertToDto(entity.get());
        } else {
            throw new RuntimeException("entity was not found");
        }
    }

    public List<T> findAll() {
        return findAllByActive(true).stream().map(e -> converter.convertToDto(e)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        Optional<B> entity = repository.findById(id);
        if (entity.isPresent()) {
            entity.get().setActive(false);
            repository.save(entity);
        } else {
            throw new RuntimeException("entity was not found");
        }
    }

    public void save(T dto) {
        B entity = converter.convertToEntity(dto);
        beforeEntitySave(entity);
        repository.save(entity);
    }

    protected void beforeEntitySave(B entity) {
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
