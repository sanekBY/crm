package by.shalukho.controller;

import by.shalukho.dto.SiteSectionDto;
import by.shalukho.service.SiteSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = SiteSectionController.CURRENT_URL)
public class SiteSectionController
        extends AbstractController<SiteSectionDto, SiteSectionService> {

    public static final String SITE_SECTION_DTO_ATTRIBUTE = "siteSectionDto";
    public static final String CURRENT_URL = "/site/section";

    @Autowired
    public SiteSectionController(final SiteSectionService siteSectionService) {
        super(siteSectionService, SiteSectionDto.class);
    }


    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String addSection(@PathVariable Long id, final Model model) {
        SiteSectionDto dto = new SiteSectionDto();
        SiteSectionDto parentSection = null;
        if (id != -1) {
            parentSection = getService().findById(id);
        }
        dto.setParentSection(parentSection);
        model.addAttribute(getAttribute(), dto);
        return "fragments/modal-fragment :: modalContents";
    }

    @Override
    public String createEntity(final SiteSectionDto dto, final Model model) {
        SiteSectionDto parentSection = dto.getParentSection();
        if (parentSection != null && parentSection.getId() != null) {
            parentSection = getService().findById(parentSection.getId());
            dto.setParentSection(parentSection);
        } else {
            dto.setParentSection(null);
        }
        getService().save(dto);
        addSuccessAlert(model, getMessage("added"));
        return goToEntityList(1, model);
    }


    @Override
    public String getEntity(@PathVariable("id") final Long id, final Model model) {
        SiteSectionDto section = getService().findById(id);
        SiteSectionDto parentSection = getService().findById(section.getParentSection().getId());
        section.setParentSection(parentSection);
        model.addAttribute(getAttribute(), section);
        return getHtml();
    }

    @Override
    protected List<SiteSectionDto> getAllEntitiesForPage(final PageRequest pageRequest) {
        return getService().findAllSimpleSection();
    }

    @Override
    protected String getAttribute() {
        return SITE_SECTION_DTO_ATTRIBUTE;
    }

    @Override
    protected String getListAttribute() {
        return "siteSections";
    }

    @Override
    protected String getListHtml() {
        return "site/sections";
    }

    @Override
    protected String getHtml() {
        return "site/section";
    }

    @Override protected String getCurrentUrl() {
        return CURRENT_URL;
    }
}
