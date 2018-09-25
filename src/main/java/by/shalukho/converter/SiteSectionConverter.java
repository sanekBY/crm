package by.shalukho.converter;

import by.shalukho.dto.SiteSectionDto;
import by.shalukho.entity.SiteSectionEntity;
import org.springframework.stereotype.Service;

@Service
public class SiteSectionConverter extends GenericConverter<SiteSectionDto, SiteSectionEntity> {

    public SiteSectionConverter() {
        super(SiteSectionEntity.class);
    }

}
