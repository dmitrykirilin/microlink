package my.project.microlink.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultKeyConverterService implements KeyConverterService{

    private final static char[] chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray();
    private final static Map<Character, Long> charToLong = new HashMap<Character, Long>(){{
        for (int i = 0; i < chars.length - 1; i++) {
            put(chars[i], (long)i);
        }
    }};

    @Override
    public String idToKey(long id) {
        long number = id;
        StringBuilder builder = new StringBuilder();
        while (number != 0L) {
            int index = (int) (number % chars.length);
            builder.append(chars[index]);
            number = number / chars.length;
        }
        return builder.reverse().toString();
    }

    @Override
    public long keyToId(String key) {
        long id = 0L;
        char[] keyChars = key.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            id = id * chars.length;
            id += charToLong.get(keyChars[i]);
        }
        return id;
    }
}
