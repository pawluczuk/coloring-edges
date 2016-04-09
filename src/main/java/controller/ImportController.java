package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportController {

    @RequestMapping("/import")
    public String importFile(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name + " !";
    }
}
