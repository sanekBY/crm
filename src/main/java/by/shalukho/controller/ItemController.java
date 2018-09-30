package by.shalukho.controller;

import by.shalukho.dto.ItemDto;
import by.shalukho.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/item")
//@PreAuthorize("hasAuthority('ADMIN')")
public class ItemController extends AbstractController<ItemDto, ItemService> {

    @Autowired
    public ItemController(final ItemService itemService) {
        super(itemService, ItemDto.class);
    }

    @Override
    protected String getAttribute() {
        return "itemDto";
    }

    @Override
    protected String getListAttribute() {
        return "items";
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
