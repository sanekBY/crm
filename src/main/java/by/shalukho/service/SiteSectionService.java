package by.shalukho.service;

import by.shalukho.converter.SiteSectionConverter;
import by.shalukho.dto.SiteSectionDto;
import by.shalukho.entity.SiteSectionEntity;
import by.shalukho.repository.SiteSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteSectionService
        extends AbstractService<SiteSectionDto, SiteSectionEntity, SiteSectionRepository> {

    public SiteSectionService(SiteSectionRepository siteSectionRepository,
                              SiteSectionConverter siteSectionConverter) {
        super(siteSectionRepository, siteSectionConverter);
    }

    public List<SiteSectionDto> findAllSimpleSection() {
        List<SiteSectionEntity> sections =
                getRepository().findAllByActiveIsTrueAndParentSectionIsNull();
        List<SiteSectionDto> siteSectionDtos = getConverter().convertAllToDto(sections);
        return siteSectionDtos;
    }
}
