package com.bookstore.tracker.controller.datamanagement;

import com.bookstore.tracker.data.dto.ResponseMessageDto;
import com.bookstore.tracker.service.datamanagement.DataManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This class is responsible for managing everything related to csv (upload of data in the system
 * export data from the system into a csv file)
 */
@Controller
@RequestMapping("/data")
@Slf4j
public class CSVControllerImpl implements CSVController {

    private final DataManagementService dataManagementService;

    @Autowired
    public CSVControllerImpl(final DataManagementService dataManagementService) {
        this.dataManagementService = dataManagementService;
    }

    /**
     * @see com.bookstore.tracker.controller.datamanagement.CSVController#uploadFile(MultipartFile, Model)
     */
    @PostMapping("/upload")
    @Override
    public String uploadFile(@RequestParam("file") final MultipartFile file, final Model bookModel) {
        final String pageToRedirectTo = "redirect:/book/list";
        String message;
        try {
            dataManagementService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            setMessageToMode(new ResponseMessageDto(message, "success"),
                    bookModel);

            return pageToRedirectTo;
            //return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDto(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            log.error("hasCSVFormat: {} and message: {}", true, message);
            setMessageToMode(new ResponseMessageDto(message, "danger"),
                    bookModel);
            e.printStackTrace();
            return pageToRedirectTo;
            //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageDto(message));
        }

        /*
        message = "Please upload a csv file!";
        log.info("hasCSVFormat: {} and message: {}", false, message);
        setMessageToMode(new ResponseMessageDto(message, "warning"), bookModel);
        return pageToRedirectTo;
         */
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDto(message));
    }

    /*
    @GetMapping("/download")
    @Override
    public ResponseEntity<Resource> getFile() {
        String filename = "books.csv";
        InputStreamResource file = new InputStreamResource(dataManagementService.loadBookData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
     */

    private void setMessageToMode(final ResponseMessageDto message, final Model model) {
        model.addAttribute("message", message);
    }
}
