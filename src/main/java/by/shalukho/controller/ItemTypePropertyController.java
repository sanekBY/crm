package by.shalukho.controller;

import by.shalukho.dto.ItemTypePropertyDto;
import by.shalukho.service.ItemTypePropertyService;
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
    public String createItemTypeProperty(@RequestBody final ItemTypePropertyDto itemTypePropertyDto) {
        itemTypePropertyService.save(itemTypePropertyDto);
        return "Item Type Property created";
    }

    @RequestMapping(value = "/api/item-type-property/{id}", method = RequestMethod.DELETE)
    public String deleteItemTypeProperty(@PathVariable("id") final Long id) {
        itemTypePropertyService.delete(id);
        return "Item Type Property deleted";
    }

    @RequestMapping(value = "/api/item-type-property/{id}", method = RequestMethod.GET)
    public ItemTypePropertyDto getItemTypeProperty(@PathVariable("id") final Long id) {
        return itemTypePropertyService.findById(id);
    }

    @RequestMapping(value = "/api/item-type-property", method = RequestMethod.GET)
    public List<ItemTypePropertyDto> getItems() {
        return itemTypePropertyService.findAll();
    }

}
