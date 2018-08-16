package by.shalukho.service;

import by.shalukho.converter.DtoDboConverter;
import by.shalukho.converter.GenericConverter;
import by.shalukho.dbo.AbstractEntity;
import by.shalukho.repository.AbstractRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class AbstractService<T, B extends AbstractEntity> {
    private AbstractRepository repository;
    private GenericConverter<T, B> converter;
    private Class<T> dtoClazz;
    private Class<B> dboClazz;

    public T findById(Long id) {
        B entity = (B) repository.findById(id).get();
        return getConverter().convertToDto(entity);
    }

    public List<T> findAll() {
        return (List<T>) repository.findAll().stream().map(e -> getConverter().convertToDto((B) e)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        B entity = (B) repository.findById(id).get();
        entity.setActive(false);
        repository.save(entity);
    }

    public void save(T dto) {
        B entity = getConverter().convertToEntity(dto);
        repository.save(entity);
    }

    public AbstractRepository getRepository() {
        return repository;
    }

    public void setRepository(AbstractRepository repository) {
        this.repository = repository;
    }

    public DtoDboConverter<T, B> getConverter() {
        return converter.getConverter(dtoClazz, dboClazz);
    }
}
