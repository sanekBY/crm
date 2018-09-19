package by.shalukho.controller;

import by.shalukho.entity.AbstractEntity;
import by.shalukho.service.AbstractService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class AbstractController<T, B extends AbstractEntity> {

    private final AbstractService service;
    private final Class<T> clazz;

    public AbstractController(final AbstractService service, final Class<T> clazz) {
        this.service = service;
        this.clazz = clazz;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createEntity(@ModelAttribute final T dto, final Model model) {
        service.save(dto);
        return goToEntityList(model);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createEntity(final Model model) {
        try {
            model.addAttribute(getAttribute(), createNewObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getHtml();
    }

    protected T createNewObject() throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEntity(@PathVariable("id") final Long id, final Model model) {
        service.delete(id);
        return goToEntityList(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEntity(@PathVariable("id") final Long id, Model model) {
        model.addAttribute(getAttribute(), service.findById(id));
        return getHtml();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getEntities(Model model) {
        return goToEntityList(model);
    }

    private String goToEntityList(final Model model) {
        model.addAttribute(getListAttribute(), service.findAll());
        return getListHtml();
    }

    protected abstract String getAttribute();

    protected abstract String getListAttribute();

    protected abstract String getListHtml();

    protected abstract String getHtml();
}
