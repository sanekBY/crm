package by.shalukho.converter;

import by.shalukho.dto.SiteReviewDto;
import by.shalukho.entity.SiteReviewEntity;
import org.springframework.stereotype.Service;

@Service
public class SiteReviewConverter extends GenericConverter<SiteReviewDto, SiteReviewEntity> {

    public SiteReviewConverter() {
        super(SiteReviewEntity.class);
    }

}
