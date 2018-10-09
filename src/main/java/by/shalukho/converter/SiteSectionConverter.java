package by.shalukho.converter;

import by.shalukho.dto.SiteSectionDto;
import by.shalukho.entity.SiteSectionEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteSectionConverter extends GenericConverter<SiteSectionDto, SiteSectionEntity> {

    public SiteSectionConverter() {
        super(SiteSectionEntity.class);
    }

    @Override
    protected SiteSectionEntity extraConvertToEntity(final SiteSectionDto siteSectionDto,
                                                     final SiteSectionEntity siteSectionEntity) {
        SiteSectionEntity entity = super.extraConvertToEntity(siteSectionDto, siteSectionEntity);
        if (siteSectionDto.getParentSection() != null) {
            entity.setParentSection(convertToEntity(siteSectionDto.getParentSection()));
        }
        entity.setSiteSections(new ArrayList<>());
        return entity;
    }

    @Override
    protected SiteSectionDto extraConvertToDto(final SiteSectionEntity siteSectionEntity,
                                               final SiteSectionDto siteSectionDto) {
        final SiteSectionDto sectionDto = super.extraConvertToDto(siteSectionEntity, siteSectionDto);
        final List<SiteSectionDto> siteSections = convertAllToDto(
                siteSectionEntity.getSiteSections().stream().filter(SiteSectionEntity::isActive).collect(
                        Collectors.toList()));
        sectionDto.setSiteSections(siteSections);

        final SiteSectionEntity parentSection = siteSectionEntity.getParentSection();
        if (parentSection != null) {
            parentSection.getSiteSections().clear();
            final SiteSectionDto parentSectionDto = convertToDto(parentSection);
            siteSectionDto.setParentSection(parentSectionDto);
        }
        return sectionDto;
    }
}
