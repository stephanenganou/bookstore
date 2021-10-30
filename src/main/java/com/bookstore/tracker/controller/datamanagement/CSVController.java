package com.bookstore.tracker.controller.datamanagement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
@RequestMapping("/data")
public interface CSVController {

    @PostMapping("/upload")
    String uploadFile(@RequestParam("file") MultipartFile file, Model bookModel);

    /*
    @GetMapping("/download")
    ResponseEntity<Resource> getFile();
     */
}
