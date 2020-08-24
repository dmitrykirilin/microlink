package my.project.microlink.controller;

import my.project.microlink.model.dto.LinkAddRequest;
import my.project.microlink.model.dto.LinkAddResponse;
import my.project.microlink.service.KeyMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class AddController {

    @Autowired
    private KeyMapperService service;

    @PostMapping
    public ResponseEntity<LinkAddResponse> addNewLink(@RequestBody LinkAddRequest link){
        if(link.getLink() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LinkAddResponse linkResponse = new LinkAddResponse(link.getLink(), service.add(link.getLink()));
        return ResponseEntity.ok(linkResponse);
    }

}
