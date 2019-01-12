package by.shalukho.controller;

import by.shalukho.SpringBootWebApplication;
import by.shalukho.config.H2TestProfileJPAConfig;
import by.shalukho.dto.AbstractDto;
import org.hamcrest.Matcher;
import org.junit.Assert;
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

    protected static final Long ID = 1L;

    private MockMvc mockMvc;
    private MediaType contentType;
    protected List<Matcher<?>> expectations;

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

    protected void checkEntityCreation(final String url, final String attr, final AbstractDto dto) {
        createPostRequest(url, attr, dto);
        checkGetRequest(url, attr, dto);
    }

    protected void createPostRequest(final String url, final String attr, final AbstractDto dto) {
        try {
            mockMvc.perform(post(url).flashAttr(attr, dto)
                                    .contentType(contentType))
                    .andExpect(status().is(302));
        } catch (Exception e) {
            Assert.fail("Post request was failed");
        }
    }

    protected void deleteRequest(final String url, final Long id) {
        doGetRequest(url + "/" + id + "/delete");
    }

    protected void checkGetRequest(final String url, final String attr, final AbstractDto dto) {
        final ResultActions resultActions;
        try {
            resultActions = doGetRequest(url + "/" + dto.getId());
            if (expectations.isEmpty()) {
                resultActions.andExpect(model().attribute(attr, dto));
            } else {
                expectations.forEach(e -> {
                    try {
                        resultActions.andDo(print()).andExpect(model().attribute(attr, e));
                    } catch (Exception e1) {
                        Assert.fail("Found unexpected value");
                    }
                });
            }
        } catch (Exception e) {
            Assert.fail("Get request was failed");
        }
    }

    private ResultActions doGetRequest(final String url) {
        ResultActions resultActions = null;
        try {
            resultActions = getMockMvc().perform(get(url));
        } catch (Exception e) {
            Assert.fail("Get request was failed");
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
