package my.project.microlink.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DefaultKeyMapperService implements KeyMapperService{

    private Map map = new HashMap<String, String>();

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public String add(String key, String link) {
        if(map.containsKey(key)){
            return "This key is already exists";
        }else {
            map.put(key, link);
            return link;
        }
    }

    @Override
    public Optional<String> getLink(String key) {
            return Optional.ofNullable(String.valueOf(map.get(key)));
    }
}
