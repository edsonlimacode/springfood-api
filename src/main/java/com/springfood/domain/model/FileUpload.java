package com.springfood.domain.model;


import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Setter
@Getter
public class FileUpload {

    private InputStream file;
    private String fileName;
    private String contentType;
    private String url;

}
