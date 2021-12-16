package novi.nl.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class BaseController {

    @GetMapping()
    public String welcome() {
        return "Welcome to this Library webservice.";
    }

}
