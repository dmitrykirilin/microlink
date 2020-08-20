package my.project.microlink.service;

import java.util.Optional;

public interface KeyMapperService {

    String add(String key, String link);
    Optional<String> getLink(String key);

}
