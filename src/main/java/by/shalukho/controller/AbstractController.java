package by.shalukho.controller;

import by.shalukho.dto.AbstractDto;
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

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public abstract class AbstractController<T extends AbstractDto, Service extends AbstractService> {

    public static final String ALERT_SUCCESS = "alert-success";
    public static final String ALERT_DANGER = "alert-danger";
    public static final String ALERT_WARNING = "alert-warning";
    public static final String PAGE_NUMBERS = "pageNumbers";
    public static final String CURRENT_URL = "currentUrl";
    public static final String CURRENT_PAGE = "currentPage";
    public static final int ITEMS_PER_LIST = 5;

    private final Service service;
    private final Class<T> clazz;

    @Autowired
    private MessageSource messageSource;

    public AbstractController(@NonNull final Service service, @NonNull final Class<T> clazz) {
        this.service = service;
        this.clazz = clazz;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createEntity(@ModelAttribute final T dto, @NonNull final Model model) {
        try {
            service.save(dto);
            addSuccessAlert(model, getMessage("saved"));
        } catch (RuntimeException ex) {
            addDangerAlert(model, ex.getMessage());
        }
        return goToEntityList(1, model);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createEntity(@NonNull final Model model) {
        try {
            model.addAttribute(getAttribute(), createNewObject());
        } catch (Exception e) {
            addDangerAlert(model, e.getMessage());
        }
        return getHtml();
    }

    protected T createNewObject() throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteEntity(@PathVariable("id") final Long id, @NonNull final Model model) {
        service.delete(id);
        addSuccessAlert(model, getMessage("deleted"));
        return goToEntityList(1, model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEntity(@PathVariable("id") final Long id, @NonNull final Model model) {
        model.addAttribute(getAttribute(), service.findById(id));
        return getHtml();
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

    protected String goToEntityList(final int pageId, @NonNull final Model model) {
        final PageRequest listPage = getListPage(pageId);
        addListToModel(model, listPage);
        model.addAttribute(CURRENT_URL, getCurrentUrl());
        model.addAttribute(CURRENT_PAGE, pageId);
        addPageNumbers(model, listPage);
        return getListHtml();
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

    protected PageRequest getListPage(final int id) {
        return PageRequest.of(id - 1, ITEMS_PER_LIST);
    }

    private void addAlert(@NonNull final Model model, @NonNull final String text, @NonNull final String alertClass) {
        model.addAttribute("notification", text);
        model.addAttribute("alertType", alertClass);
    }

    protected void addSuccessAlert(@NonNull final Model model, @NonNull final String text) {
        addAlert(model, text, ALERT_SUCCESS);
    }

    protected void addDangerAlert(@NonNull final Model model, @NonNull final String text) {
        addAlert(model, text, ALERT_DANGER);
    }

    protected void addWarningAlert(@NonNull final Model model, @NonNull final String text) {
        addAlert(model, text, ALERT_WARNING);
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
