package com.springfood.infrastructure.service.storage;


import com.springfood.core.storage.StorageProperties;
import com.springfood.domain.interfaces.FileStorageService;
import com.springfood.domain.model.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URL;

public class S3FileStorageService implements FileStorageService {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private StorageProperties properties;

    @Override
    public void upload(FileUpload fileUpload) {

        try {
            String path = getFilePath(fileUpload.getFileName());

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(properties.getBucket())
                    .key(path)
                    .contentType(fileUpload.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileUpload.getFile().readAllBytes()));

        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar a imagem para o S3: " + e.getMessage(), e);
        }

    }

    @Override
    public void remove(String fileName) {
        try {
            String path = getFilePath(fileName);

            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(properties.getBucket())
                    .key(path)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Erro ao remover arquivo do S3: " + e.getMessage(), e);
        }
    }


    @Override
    public FileUpload getInputStream(String fileName) {

        try {
            String path = getFilePath(fileName);

            URL url = s3Client.utilities()
                    .getUrl(GetUrlRequest.builder()
                            .bucket(properties.getBucket())
                            .key(path)
                            .build());

            FileUpload fileUpload = new FileUpload();
            fileUpload.setUrl(url.toString());

            return fileUpload;
        } catch (Exception e) {
            throw new StorageException("Erro ao obter URL do arquivo no S3: " + e.getMessage(), e);
        }
    }

    private String getFilePath(String fileName) {
        return this.properties.getFold() + "/" + fileName;
    }

}
