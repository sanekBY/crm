package by.shalukho.controllers.item;

import by.shalukho.SpringBootWebApplication;
import by.shalukho.config.H2TestProfileJPAConfig;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class, H2TestProfileJPAConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public abstract class AbstractTest {

    public static final long ID = 1L;
    public Map<String, Matcher<?>> expectations;
    private MockMvc mockMvc;
    public MediaType contentType;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                      this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() {
        this.contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                         MediaType.APPLICATION_JSON.getSubtype(),
                                         Charset.forName("utf8"));
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.expectations = new HashMap<>();
    }

    protected void createPostRequest(String url, Object o) {
        try {
            mockMvc.perform(post(url)
                                    .content(convertToJson(o))
                                    .contentType(contentType))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String convertToJson(Object o) {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        try {
            this.mappingJackson2HttpMessageConverter.write(
                    o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        } catch (Exception e) {
            throw new RuntimeException("Unable to convert to json");
        }
        return mockHttpOutputMessage.getBodyAsString();
    }

    protected void checkGetRequest(String url) {
        final ResultActions resultActions = getResultActions(url);

        expectations.forEach((k, v) -> {
            try {
                resultActions.andExpect(jsonPath(k, v));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private ResultActions getResultActions(final String url) {
        ResultActions resultActions = null;
        try {
            resultActions = getMockMvc().perform(get(url))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultActions;
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public void setMockMvc(final MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

}
