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
import org.springframework.web.multipart.MultipartFile;

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
        createFileService=new FileServiceImpl(propertiesMock);
    }

    @Test(expected = StorageException.class)
    public void addMemWithNullFileTest(){
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(any(Path.class));
        createFileService.store(null,"testFile","jpeg");
    }

    @Test(expected = StorageException.class)
    public void addMemWithEmptyFileTest(){
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(any(Path.class));
        when(fileMock.isEmpty()).thenReturn(true);
        createFileService.store(fileMock,"testFile","jpeg");
    }

    @Test(expected = StorageException.class)
    public void addMemWithTooBigFile() throws IOException {
        when(fileMock.getInputStream()).thenReturn(any(InputStream.class));
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(any(Path.class));
        when(fileMock.getSize()).thenReturn((long) 22020096);//21 MB
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
