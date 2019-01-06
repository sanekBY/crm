package by.shalukho.repository;

import by.shalukho.entity.ItemEntity;
import by.shalukho.entity.SiteCompanyContactsEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteCompanyContactsRepository extends JpaRepository<SiteCompanyContactsEntity, Long> {
    Optional<SiteCompanyContactsEntity> findByActiveIsTrueAndId(Long id);

    List<SiteCompanyContactsEntity> findAllByActiveIsTrue();

    Page<SiteCompanyContactsEntity> findAllByActiveIsTrue(@NonNull final Pageable page);
}
