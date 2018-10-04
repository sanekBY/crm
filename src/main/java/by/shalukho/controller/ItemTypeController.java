package by.shalukho.controller;

import by.shalukho.dto.ItemTypeDto;
import by.shalukho.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/item-type")
public class ItemTypeController extends AbstractController<ItemTypeDto> {

    public static final String ITEM_TYPE_DTO_ATTRIBUTE = "itemTypeDto";

    @Override
    public String createEntity(final Model model) {
        ItemTypeDto itemTypeDto = new ItemTypeDto();
        model.addAttribute("itemTypeDto", itemTypeDto);
        return "fragments/item-type-form :: itemType";
    }

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
