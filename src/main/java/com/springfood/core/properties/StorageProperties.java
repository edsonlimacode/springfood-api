package com.springfood.core.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("foodapi.storage")
public class StorageProperties {

    private String accessKey;
    private String accessKeySecret;
    private String bucket;
    private String region;
    private String fold;
    private LocationType locationType = LocationType.LOCAL;

    public enum LocationType {
        LOCAL, S3
    }
}
