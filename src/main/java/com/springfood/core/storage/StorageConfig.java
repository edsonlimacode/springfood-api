package com.springfood.core.storage;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.springfood.domain.interfaces.FileStorageService;
import com.springfood.infrastructure.service.storage.LocalFileStorageService;
import com.springfood.infrastructure.service.storage.S3FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties properties;

    @Bean
    public AmazonS3 amazonS3Config() {

        var credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getAccessKeySecret());

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(properties.getRegion())
                .build();
    }

    @Bean
    public FileStorageService fileStorageService() {
        if (properties.getLocationType().equals(StorageProperties.LocationType.LOCAL)) {
            return new LocalFileStorageService();
        } else {
            return new S3FileStorageService();
        }
    }

}
