package my.project.microlink.controller;

import my.project.microlink.model.Link;
import my.project.microlink.service.KeyMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/{key}")
public class RedirectController {

    @Autowired
    private KeyMapperService service;

    @GetMapping
    public void redirect(@PathVariable("key") String key,
                                        HttpServletResponse response) {
        if (!service.getLink(key).isPresent()) {
            response.setStatus(404);
        }
        Link link = (Link) service.getLink(key).get();
        response.setHeader("Location", link.getText());
        response.setStatus(302);
    }
}
