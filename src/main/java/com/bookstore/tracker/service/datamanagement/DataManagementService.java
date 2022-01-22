package com.bookstore.tracker.service.datamanagement;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (Book related) for classes which will implement it.
 */
@Service
public interface DataManagementService {

    /**
     * Method is used to save a csvFile containing book information into the system.
     */
    void save(final MultipartFile csvFile) throws IOException;
}
