package by.shalukho.repository;

import by.shalukho.entity.SiteCompanyContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteCompanyContactsRepository extends JpaRepository<SiteCompanyContactsEntity, Long> {
    Optional<SiteCompanyContactsEntity> findByActiveAndId(boolean active, Long id);

    List<SiteCompanyContactsEntity> findAllByActive(boolean active);
}
