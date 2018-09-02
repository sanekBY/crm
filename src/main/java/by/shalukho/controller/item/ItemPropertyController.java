package by.shalukho.controller.item;

import by.shalukho.controller.AbstractController;
import by.shalukho.dto.item.ItemPropertyDto;
import by.shalukho.entity.items.ItemPropertyEntity;
import by.shalukho.service.item.ItemPropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/item-property")
public class ItemPropertyController extends AbstractController<ItemPropertyDto, ItemPropertyEntity> {


    public static final String ITEM_PROPERTY_DTO_ATTRIBUTE = "itemPropertyDto";

    public ItemPropertyController(final ItemPropertyService itemPropertyService) {
        super(itemPropertyService, ItemPropertyDto.class);
    }

    @Override protected String getAttribute() {
        return ITEM_PROPERTY_DTO_ATTRIBUTE;
    }

    @Override protected String getListAttribute() {
        return "itemProperties";
    }

    @Override protected String getListHtml() {
        return "/item-property/item-property";
    }

    @Override protected String getHtml() {
        return null;
    }
}