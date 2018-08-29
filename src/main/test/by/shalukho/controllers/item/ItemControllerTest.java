package by.shalukho.controllers.item;


import by.shalukho.dto.item.ItemDto;
import by.shalukho.dto.item.ItemPropertyDto;
import by.shalukho.dto.item.ItemTypeDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class ItemControllerTest extends AbstractTest {

    public static final String ITEM_TYPE_NAME = "Name";
    public static final String ITEM_NAME = "Simple mug";
    public static final String ITEM_DESCRIPTION = "No Descr";
    public static final BigDecimal ITEM_PRICE_PRICE = new BigDecimal(1000);
    public static final String API_ITEM_TYPE_WITHOUT_ID = "/api/item-type";
    public static final String API_ITEM_WITHOUT_ID = "/api/item";
    public static final String API_ITEM_TYPE_PROPERTY_WITHOUT_ID = "/api/item-type-property";
    public static final String ITEM_TYPE_PROPERTY_NAME = "Color";
    public static final long SECOND_ID = 2l;
    public static final String SECOND_ITEM_TYPE_PROPERTY_NAME = "Size";
    public static final String SECOND_ITEM_TYPE_NAME = "Paper";

    @Test
    public void checkItemTypeCreation() {
        ItemTypeDto itemTypeDto = createItemType(ID, ITEM_TYPE_NAME);

        createPostRequest(API_ITEM_TYPE_WITHOUT_ID, itemTypeDto);

        expectations.put("$.id", is(itemTypeDto.getId().intValue()));
        expectations.put("$.name", is(itemTypeDto.getName()));

        checkGetRequest(API_ITEM_TYPE_WITHOUT_ID + "/" + ID);
    }


    @Test
    public void checkItemCreation() {
        ItemTypeDto itemTypeDto = createItemType(ID, ITEM_TYPE_NAME);
        ItemDto itemDto = createItem(ID, ITEM_NAME, itemTypeDto);

        createPostRequest(API_ITEM_TYPE_WITHOUT_ID, itemTypeDto);
        createPostRequest(API_ITEM_WITHOUT_ID, itemDto);

        expectations.put("$.id", is(itemDto.getId().intValue()));
        expectations.put("$.name", is(itemDto.getName()));
        expectations.put("$.description", is(itemDto.getDescription()));
        expectations.put("$.itemType.id", is(itemTypeDto.getId().intValue()));
        expectations.put("$.itemType.name", is(itemTypeDto.getName()));

        checkGetRequest(API_ITEM_WITHOUT_ID + "/" + ID);
    }

    @Test
    public void checkItemTypeWithPropertyCreation() {
        ItemTypeDto itemTypeDto = createItemType(ID, ITEM_TYPE_NAME);

        ItemPropertyDto itemPropertyDto = createItemProperty(ID, ITEM_TYPE_PROPERTY_NAME);
        List<ItemPropertyDto> itemProperties = new ArrayList<>();
        itemProperties.add(itemPropertyDto);

        itemTypeDto.setItemProperties(itemProperties);

        createPostRequest(API_ITEM_TYPE_PROPERTY_WITHOUT_ID, itemPropertyDto);
        createPostRequest(API_ITEM_TYPE_WITHOUT_ID, itemTypeDto);

        expectations.put("$.id", is(itemTypeDto.getId().intValue()));
        expectations.put("$.name", is(itemTypeDto.getName()));
        expectations.put("$.itemProperties[0].id", is(itemPropertyDto.getId().intValue()));
        expectations.put("$.itemProperties[0].name", is(itemPropertyDto.getName()));

        checkGetRequest(API_ITEM_TYPE_WITHOUT_ID + "/" + ID);
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

        createPostRequest(API_ITEM_TYPE_PROPERTY_WITHOUT_ID, itemPropertyDto);
        createPostRequest(API_ITEM_TYPE_PROPERTY_WITHOUT_ID, secondItemPropertyDto);

        createPostRequest(API_ITEM_TYPE_WITHOUT_ID, itemTypeDto);
        createPostRequest(API_ITEM_TYPE_WITHOUT_ID, secondItemTypeDto);

        expectations.put("$.id", is(itemTypeDto.getId().intValue()));
        expectations.put("$.name", is(itemTypeDto.getName()));
        expectations.put("$.itemProperties[0].id", is(itemPropertyDto.getId().intValue()));
        expectations.put("$.itemProperties[0].name", is(itemPropertyDto.getName()));
        expectations.put("$.itemProperties[1].id", is(secondItemPropertyDto.getId().intValue()));
        expectations.put("$.itemProperties[1].name", is(secondItemPropertyDto.getName()));

        checkGetRequest(API_ITEM_TYPE_WITHOUT_ID + "/" + ID);

        expectations.clear();
        expectations.put("$.id", is(secondItemTypeDto.getId().intValue()));
        expectations.put("$.name", is(secondItemTypeDto.getName()));
        expectations.put("$.itemProperties[0].id", is(itemPropertyDto.getId().intValue()));
        expectations.put("$.itemProperties[0].name", is(itemPropertyDto.getName()));

        checkGetRequest(API_ITEM_TYPE_WITHOUT_ID + "/" + SECOND_ID);
    }


    private ItemTypeDto createItemType(Long id, String name) {
        ItemTypeDto itemTypeDto = new ItemTypeDto();
        itemTypeDto.setId(id);
        itemTypeDto.setName(name);
        return itemTypeDto;
    }

    private ItemDto createItem(Long id, String name, ItemTypeDto itemTypeDto) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(id);
        itemDto.setItemType(itemTypeDto);
        itemDto.setName(name);
        itemDto.setDescription(ITEM_DESCRIPTION);
        itemDto.setPrice(ITEM_PRICE_PRICE);
        return itemDto;
    }

    private ItemPropertyDto createItemProperty(Long id, String name) {
        ItemPropertyDto itemPropertyDto = new ItemPropertyDto();
        itemPropertyDto.setId(id);
        itemPropertyDto.setName(name);
        return itemPropertyDto;
    }

}
