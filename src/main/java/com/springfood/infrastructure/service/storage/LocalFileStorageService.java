package com.springfood.infrastructure.service.storage;

import com.springfood.domain.model.FileUpload;
import com.springfood.domain.interfaces.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

//@Service
public class LocalFileStorageService implements FileStorageService {

    @Value("${foodapi.upload.local}")
    private Path localFilepath;

    @Override
    public void upload(FileUpload fileUpload) {
        try {
            Path path = this.localFilepath.resolve(fileUpload.getFileName());

            FileCopyUtils.copy(fileUpload.getFile(), Files.newOutputStream(path));

        } catch (Exception e) {
            throw new StorageException("Não foi possível salvar o arquivo enviado.", e);
        }
    }

    @Override
    public void remove(String fileName) {
        try {
            Path path = this.localFilepath.resolve(fileName);
            Files.deleteIfExists(path);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir o arquivo enviado.", e);
        }
    }

    @Override
    public FileUpload getInputStream(String fileName) {
        try {
            Path path = this.localFilepath.resolve(fileName);

            FileUpload fileUpload = new FileUpload();
            fileUpload.setFile(Files.newInputStream(path));

            return fileUpload;
        } catch (Exception e) {
            throw new StorageException("Não foi possível ler o arquivo.", e);
        }
    }
}
