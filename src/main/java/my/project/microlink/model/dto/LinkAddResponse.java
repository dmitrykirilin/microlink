package my.project.microlink.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkAddResponse {
    private String link;
    private String key;
}
