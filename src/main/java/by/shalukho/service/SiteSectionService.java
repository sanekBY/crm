package by.shalukho.service;

import by.shalukho.converter.SiteSectionConverter;
import by.shalukho.dto.SiteSectionDto;
import by.shalukho.entity.SiteSectionEntity;
import by.shalukho.repository.SiteSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<SiteSectionEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<SiteSectionEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }
}
