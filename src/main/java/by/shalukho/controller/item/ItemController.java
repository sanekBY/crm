package by.shalukho.controller.item;

import by.shalukho.dto.item.ItemDto;
import by.shalukho.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('ADMIN')")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/api/item", method = RequestMethod.POST)
    public String createItem(@RequestBody final ItemDto itemDto) {
        itemService.save(itemDto);
        return "Item is created";
    }

    @RequestMapping(value = "/api/item/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable("id") final Long id) {
        itemService.delete(id);
        return "Item is deleted";
    }

    @RequestMapping(value = "/api/item/{id}", method = RequestMethod.GET)
    public ItemDto getItem(@PathVariable("id") final Long id) {
        return itemService.findById(id);
    }

    @RequestMapping(value = "/api/item", method = RequestMethod.GET)
    public List<ItemDto> getItems() {
        return itemService.findAll();
    }

}
