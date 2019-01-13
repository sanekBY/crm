package by.shalukho.service;

import by.shalukho.converter.SiteStockConverter;
import by.shalukho.dto.SiteStockDto;
import by.shalukho.entity.SiteStockEntity;
import by.shalukho.repository.SiteStockRepository;
import org.springframework.stereotype.Service;

@Service
public class SiteStockService
        extends AbstractService<SiteStockDto, SiteStockEntity, SiteStockRepository> {

    public SiteStockService(SiteStockRepository siteStockRepository,
                            SiteStockConverter siteStockConverter) {
        super(siteStockRepository, siteStockConverter);
    }
}
