package by.shalukho.controller;

import by.shalukho.dto.AbstractDto;
import by.shalukho.entity.ImageEntity;
import by.shalukho.service.AbstractService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public abstract class AbstractController<T extends AbstractDto, Service extends AbstractService> {

    private static final String ALERT_SUCCESS = "alert-success";
    private static final String ALERT_DANGER = "alert-danger";
    private static final String ALERT_WARNING = "alert-warning";
    private static final String PAGE_NUMBERS = "pageNumbers";
    private static final String CURRENT_URL = "currentUrl";
    private static final String CURRENT_PAGE = "currentPage";
    private static final int ITEMS_PER_LIST = 5;

    private final Service service;
    private final Class<T> clazz;

    @Autowired
    private MessageSource messageSource;

    public AbstractController(@NonNull final Service service, @NonNull final Class<T> clazz) {
        this.service = service;
        this.clazz = clazz;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createEntity(@ModelAttribute final T dto,
                               @NonNull final RedirectAttributes redirectAttributes) {
        return createEntity(dto, redirectAttributes, service);
    }

    protected String createEntity(@ModelAttribute final T dto,
                                @NonNull final RedirectAttributes redirectAttributes, final Service service) {
        try {
            service.save(dto);
            addSuccessAlert(redirectAttributes, getMessage("saved"));
        } catch (RuntimeException ex) {
            addDangerAlert(redirectAttributes, ex.getMessage());
        }
        return redirectToList(1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createEntity(@NonNull final Model model, @NonNull final RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute(getAttribute(), createNewObject());
        } catch (Exception e) {
            addDangerAlert(redirectAttributes, e.getMessage());
        }
        return getHtml();
    }

    private T createNewObject() throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteEntity(@PathVariable("id") final Long id,
                               @NonNull final RedirectAttributes redirectAttributes) {
        service.delete(id);
        addSuccessAlert(redirectAttributes, getMessage("deleted"));
        return redirectToList(1);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEntity(@PathVariable("id") final Long id, @NonNull final Model model) {
        model.addAttribute(getAttribute(), getEntity(id));
        model.addAttribute(CURRENT_URL, getCurrentUrl());
        return getHtml();
    }

    protected T getEntity(@PathVariable("id") final Long id) {
        return (T) service.findById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getEntities(@NonNull final Model model) {
        int currentPage = 1;
        return goToEntityList(currentPage, model);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String getEntities(@PathVariable("id") Optional<Integer> page, @NonNull final Model model) {
        int currentPage = page.orElse(1);
        return goToEntityList(currentPage, model);
    }

    private String goToEntityList(final int pageId, @NonNull final Model model) {
        final PageRequest listPage = getListPage(pageId);
        addListAttributes(pageId, model, listPage);
        return getListHtml();
    }

    private void addListAttributes(final int pageId, @NonNull final Model model, final PageRequest listPage) {
        addListToModel(model, listPage);
        model.addAttribute(CURRENT_URL, getCurrentUrl());
        model.addAttribute(CURRENT_PAGE, pageId);
        addAdditionalListAttributes(model);
        addPageNumbers(model, listPage);
    }

    protected void addAdditionalListAttributes(@NonNull final Model model) {
    }


    protected String redirectToList(final int pageId) {
        return "redirect:" + getCurrentUrl() + "/list/" + pageId;
    }

    protected void addListToModel(@NonNull final Model model, @NonNull final PageRequest listPage) {
        model.addAttribute(getListAttribute(), getAllEntitiesForPage(listPage));
    }

    protected Model addPageNumbers(@NonNull final Model model, @NonNull final PageRequest listPage) {
        return model.addAttribute(PAGE_NUMBERS, service.getPageNumbers(listPage));
    }

    protected String getMessage(@NonNull final String msg) {
        return messageSource.getMessage(msg, null, new Locale("ru"));
    }

    protected List<T> getAllEntitiesForPage(@NonNull final PageRequest pageRequest) {
        return service.findPaginated(pageRequest);
    }

    private PageRequest getListPage(final int id) {
        return PageRequest.of(id - 1, ITEMS_PER_LIST);
    }

    private void addAlert(@NonNull final RedirectAttributes redirectAttributes,
                          @NonNull final String text,
                          @NonNull final String alertClass) {
        redirectAttributes.addFlashAttribute("notification", text);
        redirectAttributes.addFlashAttribute("alertType", alertClass);
    }

    protected void addSuccessAlert(@NonNull final RedirectAttributes redirectAttributes, @NonNull final String text) {
        addAlert(redirectAttributes, text, ALERT_SUCCESS);
    }

    protected void addDangerAlert(@NonNull final RedirectAttributes redirectAttributes, @NonNull final String text) {
        addAlert(redirectAttributes, text, ALERT_DANGER);
    }

    protected void addWarningAlert(@NonNull final RedirectAttributes redirectAttributes, @NonNull final String text) {
        addAlert(redirectAttributes, text, ALERT_WARNING);
    }

    protected abstract String getAttribute();

    protected abstract String getListAttribute();

    protected abstract String getListHtml();

    protected abstract String getHtml();

    protected abstract String getCurrentUrl();

    public Service getService() {
        return service;
    }
}
