package by.shalukho.controllers.item;


import by.shalukho.dto.item.ItemDto;
import by.shalukho.dto.item.ItemTypeDto;
import by.shalukho.dto.item.ItemTypePropertyDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerTest extends AbstractTest {

    public static final long ID = 1L;
    public static final String ITEM_TYPE_NAME = "Name";
    public static final String ITEM_NAME = "Simple mug";
    public static final String ITEM_DESCRIPTION = "No Descr";
    public static final BigDecimal ITEM_PRICE_PRICE = new BigDecimal(1000);
    public static final String API_ITEM_TYPE_WITHOUT_ID = "/api/item-type";
    public static final String API_ITEM_WITHOUT_ID = "/api/item";
    public static final String API_ITEM_TYPE_PROPERTY_WITHOUT_ID = "/api/item-type-property";
    public static final String ITEM_TYPE_PROPERTY_NAME = "Color";

    @Test
    public void checkItemTypeServiceCreation() throws Exception {
        ItemTypeDto itemTypeDto = createItemType();

        createPostRequest(API_ITEM_TYPE_WITHOUT_ID, this.json(itemTypeDto));

        getMockMvc().perform(get(API_ITEM_TYPE_WITHOUT_ID + "/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(itemTypeDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(itemTypeDto.getName())));
    }


    @Test
    public void checkItemCreation() throws Exception {
        ItemTypeDto itemTypeDto = createItemType();
        ItemDto itemDto = createItem(itemTypeDto);

        createPostRequest(API_ITEM_TYPE_WITHOUT_ID, this.json(itemTypeDto));
        createPostRequest(API_ITEM_WITHOUT_ID, this.json(itemDto));

        getMockMvc().perform(get(API_ITEM_WITHOUT_ID + "/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(itemDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(itemDto.getName())))
                .andExpect(jsonPath("$.description", is(itemDto.getDescription())))
                .andExpect(jsonPath("$.itemType.id", is(itemTypeDto.getId().intValue())))
                .andExpect(jsonPath("$.itemType.name", is(itemTypeDto.getName())));
    }

    @Test
    public void checkItemTypeWithPropertyCreation() throws Exception {
        ItemTypeDto itemTypeDto = createItemType();
        ItemTypePropertyDto itemTypePropertyDto = createItemTypeProperty();
        List<ItemTypePropertyDto> itemTypeProperties = new ArrayList<>();
        itemTypeProperties.add(itemTypePropertyDto);
        itemTypeDto.setItemTypeProperties(itemTypeProperties);

        createPostRequest(API_ITEM_TYPE_PROPERTY_WITHOUT_ID, this.json(itemTypePropertyDto));
        createPostRequest(API_ITEM_TYPE_WITHOUT_ID, this.json(itemTypeDto));

        getMockMvc().perform(get(API_ITEM_TYPE_WITHOUT_ID + "/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(itemTypeDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(itemTypeDto.getName())))
                .andExpect(jsonPath("$.itemTypeProperties[0].id", is(itemTypePropertyDto.getId().intValue())))
                .andExpect(jsonPath("$.itemTypeProperties[0].name", is(itemTypePropertyDto.getName())));
    }


    private ItemTypeDto createItemType() {
        ItemTypeDto itemTypeDto = new ItemTypeDto();
        itemTypeDto.setId(ID);
        itemTypeDto.setName(ITEM_TYPE_NAME);
        return itemTypeDto;
    }

    private ItemDto createItem(ItemTypeDto itemTypeDto) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(ID);
        itemDto.setItemType(itemTypeDto);
        itemDto.setName(ITEM_NAME);
        itemDto.setDescription(ITEM_DESCRIPTION);
        itemDto.setPrice(ITEM_PRICE_PRICE);
        return itemDto;
    }

    private ItemTypePropertyDto createItemTypeProperty() {
        ItemTypePropertyDto itemTypePropertyDto = new ItemTypePropertyDto();
        itemTypePropertyDto.setId(ID);
        itemTypePropertyDto.setName(ITEM_TYPE_PROPERTY_NAME);
        return itemTypePropertyDto;
    }

}
