package com.elka.coloringedges.controller;

import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.service.GraphService;
import com.elka.coloringedges.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@Controller
public class FileUploadController {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    GraphService graphService;
    @Autowired
    ImportService importService;

    @RequestMapping(method = RequestMethod.GET, value = "/upload")
    public String provideUploadInfo() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public @ResponseBody Graph handleFileUpload(@RequestParam("file") MultipartFile file,
                           RedirectAttributes redirectAttributes) {

        String originalFileName = file.getOriginalFilename();
        if(!file.isEmpty()) {
            try {
                Graph graph = graphService.buildGraph(file);
                graph = graphService.colorEdges(graph);
                log.info("Max graph colors provided by user: " + graph.getMaxColors());
                log.info("Max graph colors calculated: " + graph.getEdgeWithMaxColor().getColor());

                redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + originalFileName + "!");
                return graph;
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", "You failed to upload " + originalFileName + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "You failed to upload " + originalFileName + " because the file was empty");
        }

        return null;
    }

}
