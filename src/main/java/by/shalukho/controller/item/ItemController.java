package by.shalukho.controller.item;

import by.shalukho.controller.AbstractController;
import by.shalukho.dto.item.ItemDto;
import by.shalukho.dto.item.ItemTypeDto;
import by.shalukho.entity.items.ItemEntity;
import by.shalukho.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/item")
//@PreAuthorize("hasAuthority('ADMIN')")
public class ItemController extends AbstractController<ItemDto, ItemEntity> {

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
