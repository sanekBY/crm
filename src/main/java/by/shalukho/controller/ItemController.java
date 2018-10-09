package by.shalukho.controller;

import by.shalukho.dto.ItemDto;
import by.shalukho.dto.ItemTypeDto;
import by.shalukho.service.ItemService;
import by.shalukho.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/item")
//@PreAuthorize("hasAuthority('ADMIN')")
public class ItemController extends AbstractController<ItemDto, ItemService> {

    public static final String ITEM_TYPES = "itemTypes";
    private final ItemTypeService itemTypeService;

    @Autowired
    public ItemController(final ItemService itemService, final ItemTypeService itemTypeService) {
        super(itemService, ItemDto.class);
        this.itemTypeService = itemTypeService;
    }

    @Override
    protected String goToEntityList(final Model model) {
        model.addAttribute(getListAttribute(), itemTypeService.findAll());
        return getListHtml();
    }

    @Override
    protected String getAttribute() {
        return "itemDto";
    }

    @Override
    protected String getListAttribute() {
        return ITEM_TYPES;
    }

    @Override
    protected String getListHtml() {
        return "/item/items";
    }

    @Override
    protected String getHtml() {
        return "/item/item";
    }
}
