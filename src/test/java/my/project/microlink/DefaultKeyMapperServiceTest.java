package my.project.microlink;

import my.project.microlink.service.DefaultKeyMapperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DefaultKeyMapperServiceTest {

    @Autowired
    private DefaultKeyMapperService service;

    private final String KEY = "aAbBcCdD";
    private final String LINK = "http://www.everonline.com";
    private final String LINK_NEW = "http://www.wow.com";


    @Test
    public void clientShouldAddNewKeyWithLink(){
        assertEquals(LINK, service.add(KEY, LINK));
        assertEquals(LINK, service.getLink(KEY).get());
        service.setMap(new HashMap<String, String>());
    }

    @Test
    public void clientShouldNotAddNewKeyIfExists(){
        service.add(KEY, LINK);
        assertEquals("This key is already exists", service.add(KEY, LINK_NEW));
        assertEquals(LINK, service.getLink(KEY).get());
        service.setMap(new HashMap<String, String>());
    }

    @Test
    public void impossibleToTakeLinkByNotExistedKey(){
        assertTrue(service.getLink(KEY).isPresent());
        service.setMap(new HashMap<String, String>());
    }
}
