package my.project.microlink;

import my.project.microlink.service.DefaultKeyMapperService;
import my.project.microlink.service.KeyConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DefaultKeyMapperServiceTest {

    private static final String KEY_A = "abc";
    private static final String KEY_B = "cde";
    private static final long ID_A = 10000000L;
    private static final long ID_B = 10000001L;
    @InjectMocks
    private DefaultKeyMapperService service;

    private final String KEY = "aAbBcCdD";
    private final String LINK_A = "http://www.everonline.com";
    private final String LINK_B = "http://www.google.com";

    @Mock
    private KeyConverterService converter;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);

        Mockito.when(converter.keyToId(KEY_A)).thenReturn(ID_A);
        Mockito.when(converter.idToKey(ID_A)).thenReturn(KEY_A);
        Mockito.when(converter.keyToId(KEY_B)).thenReturn(ID_B);
        Mockito.when(converter.idToKey(ID_B)).thenReturn(KEY_B);

    }

    @Test
    public void clientShouldAddNewLinks(){
        String keyA = service.add(LINK_A);
        assertEquals(LINK_A, service.getLink(keyA));
        String keyB = service.add(LINK_B);
        assertEquals(LINK_B, service.getLink(keyB));
    }

    @Test
    public void impossibleToTakeLinkByNotExistedKey(){
        assertTrue(service.getLink(KEY).isPresent());
//        service.setMap(new HashMap<String, String>());
    }
}
