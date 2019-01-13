package by.shalukho.repository;

import by.shalukho.entity.AbstractNamedEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractNamedRepository<T extends AbstractNamedEntity> extends AbstractRepository<T> {
}
