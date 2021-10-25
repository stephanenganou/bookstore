package com.bookstore.tracker.service.datamanagement;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Service
public interface DataManagementService {
    void save(MultipartFile file);

    ByteArrayInputStream loadBookData();
}
