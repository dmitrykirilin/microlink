package my.project.microlink.service;

import org.springframework.stereotype.Component;


public interface KeyConverterService {
    String idToKey(long id);

    long keyToId(String key);
}
