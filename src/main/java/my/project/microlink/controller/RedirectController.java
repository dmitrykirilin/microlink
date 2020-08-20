package my.project.microlink.controller;

import my.project.microlink.service.KeyMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

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
        response.setHeader("Location", service.getLink(key).get());
        response.setStatus(302);
    }
}
