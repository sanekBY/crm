package by.shalukho.service;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dto.AbstractDto;
import by.shalukho.entity.AbstractNamedEntity;
import by.shalukho.repository.AbstractRepository;
import by.shalukho.specification.SpecificationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Optional;

public abstract class AbstractUniqueNameService<T extends AbstractDto, B extends AbstractNamedEntity, CustomRepository extends AbstractRepository>
        extends
        AbstractService<T, B, CustomRepository> implements ServiceWithUniqueName<B> {

    @Autowired
    private MessageSource messageSource;

    public AbstractUniqueNameService(final CustomRepository repository, final GenericConverter<T, B> converter) {
        super(repository, converter);
    }

    @Override
    public Optional<B> findByName(final String name) {
        return getRepository().findOne(SpecificationFilter.like(AbstractNamedEntity::getName, name));
    }

    @Override
    protected void beforeEntitySave(final B entity) {
        if (entity.getId() == null) {
            checkForDuplicateName(entity);
        }
        super.beforeEntitySave(entity);
    }

    private void checkForDuplicateName(final B entity) {
        final Optional<B> namedEntity = findByName(entity.getName());
        if (namedEntity.isPresent()) {
            String message = messageSource.getMessage("object.with.name.exists", null, new Locale("ru"));
            throw new RuntimeException(message);
        }
    }
}
