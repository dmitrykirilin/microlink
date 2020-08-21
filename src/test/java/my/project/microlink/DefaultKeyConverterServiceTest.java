package my.project.microlink;

import my.project.microlink.service.DefaultKeyConverterService;
import my.project.microlink.service.KeyConverterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
@SpringBootTest
public class DefaultKeyConverterServiceTest {

    @Autowired
    private DefaultKeyConverterService service;

    @Test
    public void givenIdShouldBeConvertedInBothDirections(){
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            long rndId = Math.abs(rnd.nextLong());
            String key = service.idToKey(rndId);
            long actual = service.keyToId(key);
            assertEquals(rndId, actual);
        }
    }
}
