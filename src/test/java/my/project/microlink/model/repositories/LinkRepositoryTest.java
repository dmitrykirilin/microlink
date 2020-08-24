package my.project.microlink.model.repositories;

import my.project.microlink.model.Link;
import my.project.microlink.model.repository.LinkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/sql/add_test_links.sql")
public class LinkRepositoryTest{

    private static final Long LINK_1_ID = 100500L;
    private static final String LINK_1_TEXT = "https://www.yandex.ru";
    private static final String LINK_SBS_TEXT = "https://www.nothing.ru";
    private static final Long LINK_NOT_FOUND_ID = 1L;

    @Autowired
    private LinkRepository repository;

    @Test
    public void findOneIfExists(){
        Optional<Link> result = repository.findById(LINK_1_ID);
        assertTrue(result.isPresent());

        Link storedLink = result.get();
        assertEquals(new Link( LINK_1_ID, LINK_1_TEXT), storedLink);
    }

    @Test
    public void findOneIfNotExists(){
        Optional<Link> result = repository.findById(LINK_NOT_FOUND_ID);
        assertFalse(result.isPresent());
    }

    @Test
    public void shouldAddNew(){
        Link link = new Link(LINK_SBS_TEXT);
        Link stored = repository.save(link);
        List<Link> allLinks = repository.findAll();
        assertEquals(4, allLinks.size());
        assertEquals(LINK_SBS_TEXT, stored.getText());
    }
}
