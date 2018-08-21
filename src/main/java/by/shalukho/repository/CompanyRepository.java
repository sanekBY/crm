package by.shalukho.repository;

import by.shalukho.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    public CompanyEntity findByActiveAndId(boolean active, Long id);

    public List<CompanyEntity> findAllByActive(boolean active);
}
