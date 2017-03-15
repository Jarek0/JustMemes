package edu.pl.pollub.service.implementation;

import edu.pl.pollub.config.StorageProperties;
import edu.pl.pollub.exception.StorageException;
import edu.pl.pollub.exception.StorageFileNotFoundException;
import edu.pl.pollub.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Dell on 2017-02-11.
 */
@Service
public class FileServiceImpl implements FileService{

    private final Path rootLocation;

    @Inject
    public FileServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file,String name,String fileType) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            if (file.getSize()/1048576>20) {//20MB
                throw new StorageException("Failed to store file " + file.getOriginalFilename()+". File have size larger than 20MB ");
            }

                Files.copy(file.getInputStream(), this.rootLocation.resolve(name+"."+fileType));


        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());

    }
    @Override
    public void delete(Path path) throws IOException {
        if(Files.exists(path))
        Files.delete(path);

        throw new StorageFileNotFoundException("file of path: "+path.getFileName()+" not exist");
    }
    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
