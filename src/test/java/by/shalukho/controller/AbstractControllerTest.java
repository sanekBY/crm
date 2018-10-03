package by.shalukho.controller;

import by.shalukho.SpringBootWebApplication;
import by.shalukho.config.H2TestProfileJPAConfig;
import by.shalukho.dto.AbstractDto;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class, H2TestProfileJPAConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public abstract class AbstractControllerTest {

    public static final Long RANDOM_VALUE = new Random().nextLong();

    private MockMvc mockMvc;
    public MediaType contentType;
    public List<Matcher<?>> expectations;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                         MediaType.APPLICATION_JSON.getSubtype(),
                                         Charset.forName("utf8"));
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        expectations = new ArrayList<>();
    }

    protected void checkEntityCreation(String url, String attr, AbstractDto dto) {
        createPostRequest(url, attr, dto);
        checkGetRequest(url, attr, dto);
    }

    protected void createPostRequest(String url, String attr, AbstractDto dto) {
        try {
            mockMvc.perform(post(url).flashAttr(attr, dto)
                                    .contentType(contentType))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void checkGetRequest(String url, String attr, AbstractDto dto) {
        final ResultActions resultActions;
        try {
            resultActions = getMockMvc().perform(get(url + "/" + dto.getId()));
            if (expectations.isEmpty()) {
                resultActions.andExpect(model().attribute(attr, dto));
            } else {
                expectations.forEach(e -> {
                    try {
                        resultActions.andDo(print()).andExpect(model().attribute(attr, e));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public void setMockMvc(final MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

}
