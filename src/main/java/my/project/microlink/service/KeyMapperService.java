package my.project.microlink.service;

import java.util.Optional;

public interface KeyMapperService<T>{

    Optional<T> getLink(String key);

    String add(String link);
}
