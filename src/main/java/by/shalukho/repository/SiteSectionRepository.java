package by.shalukho.repository;

import by.shalukho.entity.SiteSectionEntity;

import java.util.List;

public interface SiteSectionRepository extends AbstractNamedRepository<SiteSectionEntity> {

    List<SiteSectionEntity> findAllByActiveIsTrueAndParentSectionIsNull();

}
