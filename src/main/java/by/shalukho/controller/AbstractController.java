package by.shalukho.controller;

import by.shalukho.dto.AbstractDto;
import by.shalukho.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

public abstract class AbstractController<T extends AbstractDto, Service extends AbstractService> {

    public static final String ALERT_SUCCESS = "alert-success";
    public static final String ALERT_DANGER = "alert-danger";
    public static final String ALERT_WARNING = "alert-warning";

    private final Service service;
    private final Class<T> clazz;

    @Autowired
    private MessageSource messageSource;

    public AbstractController(final Service service, final Class<T> clazz) {
        this.service = service;
        this.clazz = clazz;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createEntity(@ModelAttribute final T dto, final Model model) {
        try {
            service.save(dto);
            addSuccessAlert(model, getMessage("saved"));
        } catch (RuntimeException ex) {
            addDangerAlert(model, ex.getMessage());
        }
        return goToEntityList(model);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createEntity(final Model model) {
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
    public String deleteEntity(@PathVariable("id") final Long id, final Model model) {
        service.delete(id);
        addSuccessAlert(model, getMessage("deleted"));
        return goToEntityList(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEntity(@PathVariable("id") final Long id, final Model model) {
        model.addAttribute(getAttribute(), service.findById(id));
        return getHtml();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getEntities(Model model) {
        return goToEntityList(model);
    }

    protected String goToEntityList(final Model model) {
        model.addAttribute(getListAttribute(), getAllEntities());
        return getListHtml();
    }

    protected String getMessage(String msg) {
        return messageSource.getMessage(msg, null, new Locale("ru"));
    }

    protected List<T> getAllEntities() {
        return service.findAll();
    }

    private void addAlert(final Model model, final String text, final String alertClass) {
        model.addAttribute("notification", text);
        model.addAttribute("alertType", alertClass);
    }

    protected void addSuccessAlert(final Model model, final String text) {
        addAlert(model, text, ALERT_SUCCESS);
    }

    protected void addDangerAlert(final Model model, final String text) {
        addAlert(model, text, ALERT_DANGER);
    }

    protected void addWarningAlert(final Model model, final String text) {
        addAlert(model, text, ALERT_WARNING);
    }

    protected abstract String getAttribute();

    protected abstract String getListAttribute();

    protected abstract String getListHtml();

    protected abstract String getHtml();

    public Service getService() {
        return service;
    }
}
