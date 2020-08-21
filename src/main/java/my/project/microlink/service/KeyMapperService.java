package my.project.microlink.service;

import java.util.Optional;

public interface KeyMapperService {

    Optional<String> getLink(String key);

    String add(String link);
}
