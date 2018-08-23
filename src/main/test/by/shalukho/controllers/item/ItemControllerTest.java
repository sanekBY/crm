package by.shalukho.controllers.item;


import by.shalukho.SpringBootWebApplication;
import by.shalukho.dto.ItemDto;
import by.shalukho.dto.ItemTypeDto;
import by.shalukho.service.ItemService;
import by.shalukho.service.ItemTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@WebAppConfiguration
public class ItemControllerTest {

    public static final long ID = 1L;
    private MockMvc mockMvc;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                  MediaType.APPLICATION_JSON.getSubtype(),
                                                  Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                      this.mappingJackson2HttpMessageConverter);
    }

    @Autowired
    ItemTypeService itemTypeService;

    @Autowired
    ItemService itemService;

    private ItemTypeDto itemTypeDto;

    private ItemDto itemDto;


    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void checkItemTypeServiceCreation() throws Exception {
        saveItemType();
        mockMvc.perform(get("/api/item-type/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(itemTypeDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(itemTypeDto.getName())));
    }

    @Test
    public void checkItemCreation() throws Exception {
        saveItemType();
        saveItem();
        mockMvc.perform(get("/api/item/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(itemDto.getId().intValue())))
                .andExpect(jsonPath("$.name", is(itemDto.getName())))
                .andExpect(jsonPath("$.description", is(itemDto.getDescription())))
                .andExpect(jsonPath("$.itemType", is("")));
    }


    private void saveItemType() {
        itemTypeDto = new ItemTypeDto();
        itemTypeDto.setId(ID);
        itemTypeDto.setName("Name");
        itemTypeService.save(itemTypeDto);
    }

    private void saveItem() {
        itemDto = new ItemDto();
        itemDto.setId(ID);
        itemDto.setItemType(itemTypeDto);
        itemDto.setName("Simple mug");
        itemDto.setDescription("No Descr");
        itemDto.setPrice(new BigDecimal(1000));
        itemService.save(itemDto);
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
