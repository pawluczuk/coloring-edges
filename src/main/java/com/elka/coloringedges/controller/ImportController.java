package com.elka.coloringedges.controller;

import com.elka.coloringedges.service.GraphService;
import com.elka.coloringedges.service.ImportService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/import")
public class ImportController {

    Logger log = Logger.getLogger(this.getClass().getName());

    GraphService graphService;
    ImportService importService;

    @RequestMapping(value = "importFile")
    public String importFile(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.info("importFile, got string: " + name);
        return "Hello " + name + " !";
    }
}
