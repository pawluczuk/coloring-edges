package com.elka.coloringedges.controller;

import com.elka.coloringedges.Application;
import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.service.GraphService;
import com.elka.coloringedges.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    GraphService graphService;
    @Autowired
    ImportService importService;

    @RequestMapping(method = RequestMethod.GET, value = "/upload")
    public String provideUploadInfo(Model model) {
        File rootFolder = new File(Application.ROOT);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );

        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    String handleFileUpload(@RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {

        String originalFileName = file.getOriginalFilename();
        if(!file.isEmpty()) {
            try {
                Graph graph = graphService.buildGraph(file);

                redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + originalFileName + "!");
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", "You failed to upload " + originalFileName + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "You failed to upload " + originalFileName + " because the file was empty");
        }
        return "redirect:upload";
    }

}
