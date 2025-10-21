package com.springfood.core.storage;


import com.springfood.domain.interfaces.FileStorageService;
import com.springfood.infrastructure.service.storage.LocalFileStorageService;
import com.springfood.infrastructure.service.storage.S3FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties properties;

   @Bean
   public S3Client amazonS3Config(StorageProperties properties) {
       AwsBasicCredentials credentials = AwsBasicCredentials.create(
               properties.getAccessKey(),
               properties.getAccessKeySecret()
       );

       return S3Client.builder()
               .credentialsProvider(StaticCredentialsProvider.create(credentials))
               .region(Region.of(properties.getRegion()))
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
