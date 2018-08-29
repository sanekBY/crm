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

import java.io.IOException;
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
public abstract class AbstractTest<T> {

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

    protected void createPostRequest(String url, String json) throws Exception {
        mockMvc.perform(post(url)
                                .content(json)
                                .contentType(contentType))
                .andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    protected void checkGetRequest(String url) throws Exception {
        final ResultActions resultActions = getMockMvc().perform(get(url))
                .andDo(print());

        expectations.forEach((k, v) -> {
            try {
                resultActions.andExpect(jsonPath(k, v));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public void setMockMvc(final MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

}
