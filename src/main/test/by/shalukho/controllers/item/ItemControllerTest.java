package by.shalukho.controllers.item;


import by.shalukho.controller.item.ItemPropertyController;
import by.shalukho.controller.item.ItemTypeController;
import by.shalukho.dto.item.ItemDto;
import by.shalukho.dto.item.ItemPropertyDto;
import by.shalukho.dto.item.ItemTypeDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemControllerTest extends AbstractTest {

    public static final String ITEM_TYPE_NAME = "Name";
    public static final String ITEM_NAME = "Simple mug";
    public static final String ITEM_DESCRIPTION = "No Descr";
    public static final BigDecimal ITEM_PRICE_PRICE = new BigDecimal(1000);
    public static final String ITEM_TYPE_WITHOUT_ID_URL = "/item-type";
    public static final String ITEM_WITHOUT_ID_URL = "/item";
    public static final String ITEM_PROPERTY_WITHOUT_ID_URL = "/item-property";
    public static final String ITEM_TYPE_PROPERTY_NAME = "Color";
    public static final long SECOND_ID = 2l;
    public static final String SECOND_ITEM_TYPE_PROPERTY_NAME = "Size";
    public static final String SECOND_ITEM_TYPE_NAME = "Paper";

    @Test
    public void checkItemTypeCreation() {
        ItemTypeDto itemTypeDto = createItemType(ID, ITEM_TYPE_NAME);

        checkEntityCreation(ITEM_TYPE_WITHOUT_ID_URL, ItemTypeController.ITEM_TYPE_DTO_ATTRIBUTE, itemTypeDto);
    }


    @Test
    public void checkEntityCreation() {
        ItemTypeDto itemTypeDto = createItemType(ID, ITEM_TYPE_NAME);
        ItemDto itemDto = createItem(ID, ITEM_NAME, itemTypeDto);

        createPostRequest(ITEM_TYPE_WITHOUT_ID_URL, ItemTypeController.ITEM_TYPE_DTO_ATTRIBUTE, itemTypeDto);

        checkEntityCreation(ITEM_WITHOUT_ID_URL, "itemDto", itemDto);
    }

    @Test
    public void checkItemTypeWithPropertyCreation() {
        ItemTypeDto itemTypeDto = createItemType(ID, ITEM_TYPE_NAME);

        ItemPropertyDto itemPropertyDto = createItemProperty(ID, ITEM_TYPE_PROPERTY_NAME);
        List<ItemPropertyDto> itemProperties = new ArrayList<>();
        itemProperties.add(itemPropertyDto);

        itemTypeDto.setItemProperties(itemProperties);

        createPostRequest(ITEM_PROPERTY_WITHOUT_ID_URL, ItemPropertyController.ITEM_PROPERTY_DTO_ATTRIBUTE,
                          itemPropertyDto);
        checkEntityCreation(ITEM_TYPE_WITHOUT_ID_URL, ItemTypeController.ITEM_TYPE_DTO_ATTRIBUTE, itemTypeDto);
    }

    @Test
    public void checkItemTypesWithMultiplePropertiesCreation() {
        ItemTypeDto itemTypeDto = createItemType(ID, ITEM_TYPE_NAME);
        ItemTypeDto secondItemTypeDto = createItemType(SECOND_ID, SECOND_ITEM_TYPE_NAME);

        ItemPropertyDto itemPropertyDto = createItemProperty(ID, ITEM_TYPE_PROPERTY_NAME);
        ItemPropertyDto secondItemPropertyDto = createItemProperty(SECOND_ID,
                                                                   SECOND_ITEM_TYPE_PROPERTY_NAME);

        List<ItemPropertyDto> itemTypeProperties = new ArrayList<>();
        List<ItemPropertyDto> secondItemTypeProperties = new ArrayList<>();
        itemTypeProperties.addAll(Arrays.asList(itemPropertyDto, secondItemPropertyDto));
        secondItemTypeProperties.add(itemPropertyDto);

        itemTypeDto.setItemProperties(itemTypeProperties);
        secondItemTypeDto.setItemProperties(secondItemTypeProperties);

        createPostRequest(ITEM_PROPERTY_WITHOUT_ID_URL, ItemPropertyController.ITEM_PROPERTY_DTO_ATTRIBUTE,
                          itemPropertyDto);
        createPostRequest(ITEM_PROPERTY_WITHOUT_ID_URL, ItemPropertyController.ITEM_PROPERTY_DTO_ATTRIBUTE,
                          secondItemPropertyDto);

        checkEntityCreation(ITEM_TYPE_WITHOUT_ID_URL, ItemTypeController.ITEM_TYPE_DTO_ATTRIBUTE, itemTypeDto);
        checkEntityCreation(ITEM_TYPE_WITHOUT_ID_URL, ItemTypeController.ITEM_TYPE_DTO_ATTRIBUTE, secondItemTypeDto);
    }


    private ItemTypeDto createItemType(Long id, String name) {
        ItemTypeDto itemTypeDto = new ItemTypeDto();
        itemTypeDto.setId(id);
        itemTypeDto.setName(name);
        itemTypeDto.setItemProperties(new ArrayList<>());
        return itemTypeDto;
    }

    private ItemDto createItem(Long id, String name, ItemTypeDto itemTypeDto) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(id);
        itemDto.setItemType(itemTypeDto);
        itemDto.setName(name);
        itemDto.setDescription(ITEM_DESCRIPTION);
        itemDto.setPrice(ITEM_PRICE_PRICE);
        itemDto.setItemProperties(new ArrayList<>());
        return itemDto;
    }

    private ItemPropertyDto createItemProperty(Long id, String name) {
        ItemPropertyDto itemPropertyDto = new ItemPropertyDto();
        itemPropertyDto.setId(id);
        itemPropertyDto.setName(name);
        return itemPropertyDto;
    }

}
