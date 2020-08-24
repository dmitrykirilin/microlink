package my.project.microlink.model.repository;

import my.project.microlink.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByText(String link);

//   Optional<Link> findById(Long id);
//
//    Link save(Link link);
//
//    List<Link> findAll();
}
