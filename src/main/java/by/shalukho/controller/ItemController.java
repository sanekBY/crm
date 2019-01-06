package by.shalukho.controller;

import by.shalukho.dto.ItemDto;
import by.shalukho.service.ItemService;
import by.shalukho.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ItemController.CURRENT_URL)
//@PreAuthorize("hasAuthority('ADMIN')")
public class ItemController extends AbstractController<ItemDto, ItemService> {

    public static final String ITEM_TYPES = "itemTypes";
    public static final String CURRENT_URL = "/item";
    private final ItemTypeService itemTypeService;

    @Autowired
    public ItemController(final ItemService itemService, final ItemTypeService itemTypeService) {
        super(itemService, ItemDto.class);
        this.itemTypeService = itemTypeService;
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
        return "item/items";
    }

    @Override
    protected String getHtml() {
        return "item/item";
    }

    @Override
    protected String getCurrentUrl() {
        return CURRENT_URL;
    }

    @Override
    protected void addListToModel(final Model model, final PageRequest listPage) {
        model.addAttribute(getListAttribute(), itemTypeService.findPaginated(listPage));
    }
}
