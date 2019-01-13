package by.shalukho.service;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.AbstractDto;
import by.shalukho.entity.AbstractEntity;
import by.shalukho.repository.AbstractRepository;
import by.shalukho.specification.SpecificationFilter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public abstract class AbstractService<T extends AbstractDto, B extends AbstractEntity, CustomRepository extends AbstractRepository>
        implements ServiceWithActive<B> {

    public final Specification<B> IS_ACTIVE_SPECIFICATION = SpecificationFilter.equal(B::isActive, "true");

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

    public List<T> findAll(final Specification specification) {
        final List<B> allEntities = repository.findAll(SpecificationFilter.and(IS_ACTIVE_SPECIFICATION, specification));
        return converter.convertAllToDto(allEntities);
    }

    public T findOne(final Specification specification) {
        Optional<B> entity = repository.findOne(specification);
        if (entity.isPresent()) {
            return converter.convertToDto(entity.get());
        } else {
            throw new RuntimeException("entity was not found");
        }
    }

    public List<T> findAll() {
        return converter.convertAllToDto(findAllByActiveIsTrue());
    }

    @Override
    public Optional<B> findByActiveIsTrueAndId(final Long id) {
        return repository.findOne(
                SpecificationFilter.and(IS_ACTIVE_SPECIFICATION,
                                        SpecificationFilter.equal(B::getId, id.toString())));
    }

    @Override
    public List<B> findAllByActiveIsTrue() {
        return repository.findAll(IS_ACTIVE_SPECIFICATION);
    }

    public List<T> findPaginated(@NonNull final PageRequest pageRequest) {
        final List<B> content = findAllByActiveIsTrue(pageRequest).getContent();
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

    public GenericConverter<T, B> getConverter() {
        return converter;
    }

    @Override
    public Page<B> findAllByActiveIsTrue(final Pageable pageable) {
        return repository.findAll(
                SpecificationFilter.and(IS_ACTIVE_SPECIFICATION), pageable);
    }
}
