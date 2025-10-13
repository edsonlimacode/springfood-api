package com.springfood.core.validation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FileContentValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    String[] types;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.types = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {

        List<String> types = Arrays.asList(this.types);

        return types.contains(file.getContentType());
    }
}
