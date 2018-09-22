package by.shalukho.service;

import by.shalukho.converter.GenericConverter;
import by.shalukho.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class AbstractService<T, B extends AbstractEntity, CustomRepository extends JpaRepository>
        implements ServiceWithActive<B> {

    private CustomRepository repository;

    private GenericConverter<T, B> converter;

    public T findById(final Long id) {
        final Optional<B> entity = findByActiveAndId(true, id);
        if (entity.isPresent()) {
            return converter.convertToDto(entity.get());
        } else {
            throw new RuntimeException("entity was not found");
        }
    }

    public List<T> findAll() {
        return findAllByActive(true).stream().map(e -> converter.convertToDto(e)).collect(Collectors.toList());
    }

    public void delete(final Long id) {
        final Optional<B> entity = repository.findById(id);
        if (entity.isPresent()) {
            B entityValue = entity.get();
            entityValue.setActive(false);
            repository.save(entityValue);
        } else {
            throw new RuntimeException("entity was not found");
        }
    }

    public void save(final T dto) {
        final B entity = converter.convertToEntity(dto);
        beforeEntitySave(entity);
        repository.save(entity);
    }

    protected void beforeEntitySave(B entity) {
    }

    public List<T> findAllById(List<Long> ids) {
        final List<B> entities = repository.findAllById(ids);
        final List<T> dtos = (List<T>) entities.stream().map(e -> converter.convertToDto(e));
        return dtos;
    }

    public CustomRepository getRepository() {
        return repository;
    }

    public void setRepository(CustomRepository repository) {
        this.repository = repository;
    }

    public GenericConverter<T, B> getConverter() {
        return converter;
    }
}
