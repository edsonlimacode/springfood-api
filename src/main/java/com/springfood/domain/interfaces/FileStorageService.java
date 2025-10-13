package com.springfood.domain.interfaces;

import com.springfood.domain.model.FileUpload;

import java.util.UUID;

public interface FileStorageService {

    void upload(FileUpload fileUpload);

    void remove(String fileName);

    FileUpload getInputStream(String fileName);

    default String generateFileName(String fileName) {
        return UUID.randomUUID() + "-" + fileName;
    }

}
