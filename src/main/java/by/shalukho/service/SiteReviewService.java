package by.shalukho.service;

import by.shalukho.converter.SiteReviewConverter;
import by.shalukho.dto.SiteReviewDto;
import by.shalukho.entity.SiteReviewEntity;
import by.shalukho.repository.SiteReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteReviewService
        extends AbstractService<SiteReviewDto, SiteReviewEntity, SiteReviewRepository> {

    public SiteReviewService(SiteReviewRepository siteReviewRepository,
                             SiteReviewConverter siteReviewConverter) {
        super(siteReviewRepository, siteReviewConverter);
    }

    @Override
    public Optional<SiteReviewEntity> findByActiveAndId(boolean active, Long id) {
        return getRepository().findByActiveAndId(active, id);
    }

    @Override
    public List<SiteReviewEntity> findAllByActive(boolean active) {
        return getRepository().findAllByActive(active);
    }
}
