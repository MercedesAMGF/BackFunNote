package com.back.funnote.contact;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void Store(MultipartFile file);
    Resource loadFile(String filename);
    void deleteAll();
    void init();

}

