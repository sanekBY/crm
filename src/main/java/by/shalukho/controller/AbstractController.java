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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createEntity(@ModelAttribute final T dto,
                               @NonNull final Model model,
                               @NonNull final RedirectAttributes redirectAttributes) {
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

    protected T createNewObject() throws InstantiationException, IllegalAccessException {
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
        model.addAttribute(getAttribute(), service.findById(id));
        model.addAttribute(CURRENT_URL, getCurrentUrl());
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

    private String goToEntityList(final int pageId, @NonNull final Model model) {
        final PageRequest listPage = getListPage(pageId);
        addListToModel(model, listPage);
        model.addAttribute(CURRENT_URL, getCurrentUrl());
        model.addAttribute(CURRENT_PAGE, pageId);
        addPageNumbers(model, listPage);
        return getListHtml();
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

    protected PageRequest getListPage(final int id) {
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
