package by.shalukho.repository;

import by.shalukho.entity.customer.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    public Optional<CompanyEntity> findByActiveAndId(boolean active, Long id);

    public List<CompanyEntity> findAllByActive(boolean active);
}
