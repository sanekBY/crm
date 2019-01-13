package by.shalukho.repository;

import by.shalukho.entity.AbstractEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity>
        extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    Page<T> findAll(@NonNull final Specification<T> specification, @NonNull final Pageable page);

}
