package com.bookstore.tracker.service.datamanagement;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public interface DataManagementService {

    void save(MultipartFile file) throws IOException;
}
