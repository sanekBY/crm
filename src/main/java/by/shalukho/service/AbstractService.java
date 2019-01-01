package by.shalukho.service;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.AbstractDto;
import by.shalukho.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public abstract class AbstractService<T extends AbstractDto, B extends AbstractEntity, CustomRepository extends JpaRepository>
        implements ServiceWithActive<B> {

    private CustomRepository repository;

    private GenericConverter<T, B> converter;

    public T findById(final Long id) {
        final Optional<B> entity = findByActiveIsTrueAndId(id);
        if (entity.isPresent()) {
            return converter.convertToDto(entity.get());
        } else {
            throw new RuntimeException("entity was not found");
        }
    }

    public List<T> findAll() {
        return findAllByActiveIsTrue().stream().map(e -> converter.convertToDto(e)).collect(Collectors.toList());
    }

    public List<T> findPaginated(@NonNull final PageRequest pageRequest) {
        final List<B> content = repository.findAll(pageRequest).getContent();
        return converter.convertAllToDto(content);
    }

    public List<Integer> getPageNumbers(@NonNull final PageRequest pageRequest) {
        final List<Integer> pageNumbers = new ArrayList<>();
        final Page page = repository.findAll(pageRequest);
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            pageNumbers.addAll(IntStream.rangeClosed(1, totalPages)
                                       .boxed()
                                       .collect(Collectors.toList()));
        }
        return pageNumbers;
    }

    public void delete(final Long id) {
        final Optional<B> entity = repository.findById(id);
        if (entity.isPresent()) {
            final B entityValue = entity.get();
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
