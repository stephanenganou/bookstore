package com.bookstore.tracker.controller.datamanagement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (csv related) for classes which will implement it.
 */
@Controller
public interface CSVController {

    /**
     * Method (route) used to upload a file into the system.
     */
    String uploadFile(@RequestParam("file") MultipartFile file, Model bookModel);

    /*
    @GetMapping("/download")
    ResponseEntity<Resource> getFile();
     */
}
