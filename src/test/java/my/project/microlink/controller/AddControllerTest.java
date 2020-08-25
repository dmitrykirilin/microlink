package my.project.microlink.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.microlink.MicrolinkApplication;
import my.project.microlink.service.KeyMapperService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MicrolinkApplication.class)
@WebAppConfiguration
public class AddControllerTest {

    private final String KEY = "key";
    private final String LINK = "link";

    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    @InjectMocks
    private AddController controller;

    @Mock
    private KeyMapperService service;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        MockitoAnnotations.initMocks(this);
        Mockito.when(service.add(LINK)).thenReturn(KEY);
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void shouldReturnKeyWhenUserGivenLink() throws Exception {
        this.mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(LINK)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapToJson(new HashMap<String, Object>() {{
                    put("link", LINK);
                    put("key", KEY);
                }})));
    }

    @Test
    public void whenUserEnterLinkShouldReturnedResultPage() throws Exception {
        this.mockMvc.perform(post("/addhtml")
                .param("link", LINK)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("link_text", LINK))
                .andExpect(model().attribute("key", KEY));
                }
    }

