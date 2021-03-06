package by.shalukho.controller;

import by.shalukho.dto.ItemPropertyDto;
import by.shalukho.service.ItemPropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ItemPropertyController.CURRENT_PAGE_URL)
public class ItemPropertyController
        extends AbstractController<ItemPropertyDto, ItemPropertyService> {

    public static final String ITEM_PROPERTY_DTO_ATTRIBUTE = "itemPropertyDto";
    public static final String CURRENT_PAGE_URL = "/item-property";

    public ItemPropertyController(final ItemPropertyService itemPropertyService) {
        super(itemPropertyService, ItemPropertyDto.class);
    }

    @Override
    protected String getAttribute() {
        return ITEM_PROPERTY_DTO_ATTRIBUTE;
    }

    @Override
    protected String getListAttribute() {
        return "itemProperties";
    }

    @Override
    protected String getListHtml() {
        return "item-property/item-property";
    }

    @Override
    protected String getHtml() {
        return null;
    }

    @Override protected String getCurrentUrl() {
        return CURRENT_PAGE_URL;
    }
}
