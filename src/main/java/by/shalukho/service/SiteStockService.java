package by.shalukho.service;

import by.shalukho.converter.SiteStockConverter;
import by.shalukho.dto.SiteStockDto;
import by.shalukho.entity.SiteStockEntity;
import by.shalukho.repository.SiteStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteStockService
        extends AbstractService<SiteStockDto, SiteStockEntity, SiteStockRepository> {

    public SiteStockService(SiteStockRepository siteStockRepository,
                            SiteStockConverter siteStockConverter) {
        super(siteStockRepository, siteStockConverter);
    }

    @Override
    public Optional<SiteStockEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<SiteStockEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }
}
