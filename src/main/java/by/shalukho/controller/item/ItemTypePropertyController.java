package by.shalukho.controller.item;

import by.shalukho.dto.item.ItemPropertyDto;
import by.shalukho.service.item.ItemTypePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemTypePropertyController {

    private final ItemTypePropertyService itemTypePropertyService;

    @Autowired
    public ItemTypePropertyController(ItemTypePropertyService itemTypePropertyService) {
        this.itemTypePropertyService = itemTypePropertyService;
    }

    @RequestMapping(value = "/api/item-type-property", method = RequestMethod.POST)
    public String createItemTypeProperty(@RequestBody final ItemPropertyDto itemPropertyDto) {
        itemTypePropertyService.save(itemPropertyDto);
        return "Item Type Property created";
    }

    @RequestMapping(value = "/api/item-type-property/{id}", method = RequestMethod.DELETE)
    public String deleteItemTypeProperty(@PathVariable("id") final Long id) {
        itemTypePropertyService.delete(id);
        return "Item Type Property deleted";
    }

    @RequestMapping(value = "/api/item-type-property/{id}", method = RequestMethod.GET)
    public ItemPropertyDto getItemTypeProperty(@PathVariable("id") final Long id) {
        return itemTypePropertyService.findById(id);
    }

    @RequestMapping(value = "/api/item-type-property", method = RequestMethod.GET)
    public List<ItemPropertyDto> getItems() {
        return itemTypePropertyService.findAll();
    }

}
