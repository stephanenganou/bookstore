package com.bookstore.tracker.controller.datamanagement;

import com.bookstore.tracker.data.dto.ResponseMessageDto;
import com.bookstore.tracker.helper.CSVHelper;
import com.bookstore.tracker.service.datamanagement.DataManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CSVControllerImpl implements CSVController {

    private final DataManagementService dataManagementService;

    @Autowired
    public CSVControllerImpl(DataManagementService dataManagementService) {
        this.dataManagementService = dataManagementService;
    }

    @Override
    public ResponseEntity<ResponseMessageDto> uploadFile(MultipartFile file) {
        String message;

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                dataManagementService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDto(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageDto(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDto(message));
    }

    @Override
    public ResponseEntity<Resource> getFile() {
        String filename = "books.csv";
        InputStreamResource file = new InputStreamResource(dataManagementService.loadBookData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
