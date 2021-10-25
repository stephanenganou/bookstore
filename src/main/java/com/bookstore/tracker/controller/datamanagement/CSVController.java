package com.bookstore.tracker.controller.datamanagement;

import com.bookstore.tracker.data.dto.ResponseMessageDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/data")
public interface CSVController {

    @PostMapping("/upload")
    ResponseEntity<ResponseMessageDto> uploadFile(@RequestParam("file") MultipartFile file);

    @GetMapping("/download")
    ResponseEntity<Resource> getFile();
}
