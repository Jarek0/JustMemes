package edu.pl.pollub.service;

import edu.pl.pollub.StorageProperties;
import edu.pl.pollub.exception.StorageException;
import edu.pl.pollub.service.implementation.FileServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by Dell on 2017-03-01.
 */
public class FileServiceTest {

    private MultipartFile fileMock;
    private StorageProperties propertiesMock;
    private FileService createFileService;
    private Path rootLocationMock;

    @Before
    public void setUp() {
        fileMock = Mockito.mock(MultipartFile.class);
        propertiesMock = Mockito.mock(StorageProperties.class);
        rootLocationMock = Mockito.mock(Path.class);
        when(propertiesMock.getLocation()).thenReturn("src/main/resources/upload-files");
        createFileService=new FileServiceImpl(propertiesMock);
    }

    @Test
    public void storeFileTest() throws IOException {
        when(fileMock.isEmpty()).thenReturn(false);
        when(fileMock.getSize()).thenReturn((long) 1048576);//1MB
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(Paths.get("src/main/resources/upload-files/testFile.jpeg"));
        when(fileMock.getInputStream()).thenReturn(any(InputStream.class));

        createFileService.store(fileMock,"testFile","jpeg");
    }

    @Test(expected = NullPointerException.class)
    public void addMemWithNullFileTest() throws IOException {
        when(fileMock.isEmpty()).thenReturn(false);
        when(fileMock.getSize()).thenReturn((long) 1048576);//1MB
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(Paths.get("src/main/resources/upload-files/testFile.jpeg"));
        when(fileMock.getInputStream()).thenReturn(any(InputStream.class));

        createFileService.store(null,"testFile","jpeg");
    }

    @Test(expected = StorageException.class)
    public void addMemWithEmptyFileTest() throws IOException {
        when(fileMock.isEmpty()).thenReturn(true);
        when(fileMock.getSize()).thenReturn((long) 1048576);//1MB
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(Paths.get("src/main/resources/upload-files/testFile.jpeg"));
        when(fileMock.getInputStream()).thenReturn(any(InputStream.class));

        createFileService.store(fileMock,"testFile","jpeg");
    }

    @Test(expected = StorageException.class)
    public void addMemWithTooBigFile() throws IOException {
        when(fileMock.isEmpty()).thenReturn(false);
        when(fileMock.getSize()).thenReturn((long) 22020096);//21 MB
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(Paths.get("src/main/resources/upload-files/testFile.jpeg"));
        when(fileMock.getInputStream()).thenReturn(any(InputStream.class));

        createFileService.store(fileMock,"testFile","jpeg");
    }

    @Test(expected = StorageException.class)
    public void addMemWithInvalidFileSize(){
        when(fileMock.getSize()).thenReturn((long) -1);
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(any(Path.class));
        createFileService.store(fileMock,"testFile","jpeg");
    }

    @Test
    public void addMemWithUnnamedFile(){
        createFileService.store(fileMock,"","jpeg");
    }

    @Test
    public void addMemWithNullFileName(){

        createFileService.store(fileMock,null,"jpeg");
    }

    @Test
    public void addMemWithFileWithoutType(){

        createFileService.store(fileMock,"testFile",null);
    }

    @Test
    public void invalidRootLocationTest(){

        createFileService.store(fileMock,"testFile",null);
    }
}
