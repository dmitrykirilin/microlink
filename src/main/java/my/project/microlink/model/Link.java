package my.project.microlink.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "links")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
    @SequenceGenerator(name = "links_sequence", allocationSize = 1, sequenceName = "link_sequence")
    private Long id;

    @NonNull
    private String text;
}
