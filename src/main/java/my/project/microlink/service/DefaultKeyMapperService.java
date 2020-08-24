package my.project.microlink.service;

import my.project.microlink.model.Link;
import my.project.microlink.model.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DefaultKeyMapperService implements KeyMapperService<Link>{

    @Autowired
    private LinkRepository repository;

    @Autowired
    private KeyConverterService converter;

    private long sequence;

    public long getSequence() {
        return (long) (Math.random()*100000000 + 10000000);
    }

    @Override
    public String add(String link) {
        String key = null;
        Optional<Link> linkFromDb = repository.findByText(link);
        if(linkFromDb.isPresent()){
            key = converter.idToKey(linkFromDb.get().getId());
            return key;
        }
        Link storageLink = repository.save(new Link(link));
        key = converter.idToKey(storageLink.getId());
        return key;
    }

    @Override
    public Optional<Link> getLink(String key) {
        long id = converter.keyToId(key);
        Optional<Link> linkById = repository.findById(id);

        return linkById;
    }
}
