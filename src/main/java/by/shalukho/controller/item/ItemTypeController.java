package by.shalukho.controller.item;

import by.shalukho.dto.item.ItemTypeDto;
import by.shalukho.service.item.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemTypeController {

    private final ItemTypeService itemTypeService;

    @Autowired
    public ItemTypeController(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }

    @RequestMapping(value = "/api/item-type", method = RequestMethod.POST)
    public String createItem(@RequestBody final ItemTypeDto itemTypeDto) {
        itemTypeService.save(itemTypeDto);
        return "Item Type created";
    }

    @RequestMapping(value = "/api/item-type/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable("id") final Long id) {
        itemTypeService.delete(id);
        return "Item Type deleted";
    }

    @RequestMapping(value = "/api/item-type/{id}", method = RequestMethod.GET)
    public ItemTypeDto getItem(@PathVariable("id") final Long id) {
        return itemTypeService.findById(id);
    }

    @RequestMapping(value = "/api/item-type", method = RequestMethod.GET)
    public List<ItemTypeDto> getItems() {
        return itemTypeService.findAll();
    }

}
