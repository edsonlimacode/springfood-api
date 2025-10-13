package com.springfood.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.springfood.core.storage.StorageProperties;
import com.springfood.domain.model.FileUpload;
import com.springfood.domain.interfaces.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

//@Service
public class S3FileStorageService implements FileStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties properties;

    @Override
    public void upload(FileUpload fileUpload) {

        try {

            String path = getFilePath(fileUpload.getFileName());

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(fileUpload.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    properties.getBucket(),
                    path,
                    fileUpload.getFile(),
                    objectMetadata);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar a imagem para o S3" + e.getMessage());
        }

    }

    @Override
    public void remove(String fileName) {
        String path = getFilePath(fileName);

        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
                properties.getBucket(),
                path);

        amazonS3.deleteObject(deleteObjectRequest);
    }


    @Override
    public FileUpload getInputStream(String fileName) {

        String path = getFilePath(fileName);

        URL url = amazonS3.getUrl(properties.getBucket(), path);

        FileUpload fileUpload = new FileUpload();
        fileUpload.setUrl(url.toString());

        return fileUpload;
    }

    private String getFilePath(String fileName) {
        String path = this.properties.getFold() + "/" + fileName;
        return path;
    }

}
