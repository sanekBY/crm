package by.shalukho.controller;

import by.shalukho.dto.ItemTypeDto;
import by.shalukho.service.ItemTypeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = ItemTypeController.CURRENT_PAGE_URL)
public class ItemTypeController extends AbstractController<ItemTypeDto, ItemTypeService> {

    public static final String ITEM_TYPE_DTO_ATTRIBUTE = "itemTypeDto";
    public static final String CURRENT_PAGE_URL = "/item-type";

    @Override
    public String createEntity(final Model model, @NonNull final RedirectAttributes redirectAttributes) {
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
        return "item/items";
    }

    @Override
    protected String getHtml() {
        return "item-type/item-type";
    }

    @Override
    protected String getCurrentUrl() {
        return CURRENT_PAGE_URL;
    }
}
