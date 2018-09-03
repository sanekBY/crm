package by.shalukho.controller.item;

import by.shalukho.controller.AbstractController;
import by.shalukho.dto.item.ItemTypeDto;
import by.shalukho.entity.items.ItemTypeEntity;
import by.shalukho.service.item.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/item-type")
public class ItemTypeController extends AbstractController<ItemTypeDto, ItemTypeEntity> {

    public static final String ITEM_TYPE_DTO_ATTRIBUTE = "itemTypeDto";

    @Autowired
    public ItemTypeController(final ItemTypeService itemTypeService) {
        super(itemTypeService, ItemTypeDto.class);
    }

    @Override
    protected String getAttribute() {
        return ITEM_TYPE_DTO_ATTRIBUTE;
    }

    @Override
    protected String getListAttribute() {
        return "iteTypes";
    }

    @Override
    protected String getListHtml() {
        return "/item-type/item-types";
    }

    @Override
    protected String getHtml() {
        return "/item-type/item-type";
    }
}
