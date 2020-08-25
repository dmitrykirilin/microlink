package my.project.microlink.controller;

import my.project.microlink.model.dto.LinkAddRequest;
import my.project.microlink.model.dto.LinkAddResponse;
import my.project.microlink.service.KeyMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddController {

    @Autowired
    private KeyMapperService service;

    @Value("${link.prefix}")
    private String prefix;

    @PostMapping(value = "add")
    @ResponseBody
    public ResponseEntity<LinkAddResponse> addNewLink(@RequestBody LinkAddRequest link){
        if(link.getLink() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LinkAddResponse linkResponse = new LinkAddResponse(link.getLink(), service.add(link.getLink()));
        return ResponseEntity.ok(linkResponse);
    }

    @GetMapping(value = "/")
    public String returnHomePage(@PathVariable(name = "error", required = false) Boolean error,
                                 Model model){
        if(Boolean.TRUE.equals(error)){
            model.addAttribute("message", "Fill this field!");
        }
        return "home";
    }

    @PostMapping(value = "addhtml")
    public String returnResultPage(@RequestParam(name = "link", required = true) String link,
                                    Model model){
        if(link == null){
            return "redirect:/home?error=true";
        }
        String key = service.add(link);
        model.addAttribute("link_text", link);
        model.addAttribute("key", key);
        model.addAttribute("prefix", prefix);
        return "result";
    }

}
