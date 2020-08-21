package my.project.microlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DefaultKeyMapperService implements KeyMapperService{

    private KeyConverterService converter;

    private long sequence;

    public long getSequence() {
        return (long) (Math.random()*100000000 + 10000000);
    }

    private Map map = new HashMap<Long, String>();

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public String add(String link) {
        long id = getSequence();
        String key = converter.idToKey(id);
        map.put(id, link);
        return key;
    }

    @Override
    public Optional<String> getLink(String key) {
        long id = converter.keyToId(key);
        return Optional.ofNullable(String.valueOf(map.get(id)));
    }
}
