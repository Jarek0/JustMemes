package edu.pl.pollub.service;

import edu.pl.pollub.config.StorageProperties;
import edu.pl.pollub.service.implementation.FileServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

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

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() throws IOException {
        fileMock = Mockito.mock(MultipartFile.class);
        propertiesMock = Mockito.mock(StorageProperties.class);
        rootLocationMock = Mockito.mock(Path.class);

        when(propertiesMock.getLocation()).thenReturn("src/main/resources/upload-files/testFiles");
        when(fileMock.getOriginalFilename()).thenReturn("testFile.jpg");
        createFileService=new FileServiceImpl(propertiesMock);
        createFileService.init();

        when(fileMock.isEmpty()).thenReturn(false);
        when(fileMock.getSize()).thenReturn((long) 1048576);//1MB
        when(rootLocationMock.resolve(eq("testFile.jpeg"))).thenReturn(Paths.get("src/main/resources/upload-files/testFiles/testFile.txt"));
        when(fileMock.getInputStream()).thenReturn(new ByteArrayInputStream(("Test file string".getBytes())));
    }

    @After
    public void cleaning(){
        try {
            createFileService.deleteAll();
        }
        catch (Exception e){

        }
    }

    @Test
    public void storeFileTest() throws IOException {
        when(fileMock.isEmpty()).thenReturn(false);
        when(fileMock.getSize()).thenReturn((long) 1048576);//1MB


            when(rootLocationMock.resolve(eq("testFile.txt"))).thenReturn(Paths.get("src/main/resources/upload-files/testFile.txt"));
            when(fileMock.getInputStream()).thenReturn(new ByteArrayInputStream(("Test file string".getBytes())));

            createFileService.store(fileMock, "testFile", "txt");

    }

    @Test
    public void addMemWithEmptyFileTest() throws IOException {

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Failed to store empty file " + fileMock.getOriginalFilename());

        when(fileMock.isEmpty()).thenReturn(true);

        createFileService.store(fileMock,"testFile","txt");
    }

    @Test
    public void addMemWithTooBigFile() throws IOException {

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Failed to store file " + fileMock.getOriginalFilename() +". File have size larger than 20MB ");

        when(fileMock.getSize()).thenReturn((long) 22020096);//21 MB

        createFileService.store(fileMock,"testFile","txt");
    }

    @Test
    public void addMemWithUnnamedFile(){
        when(fileMock.getOriginalFilename()).thenReturn(".txt");
        when(rootLocationMock.resolve(eq(".txt"))).thenReturn(Paths.get("src/main/resources/upload-files/.txt"));

        createFileService.store(fileMock,"","txt");
    }

    @Test
    public void addMemWithNullFileName(){
        when(fileMock.getOriginalFilename()).thenReturn(null);
        when(rootLocationMock.resolve(eq(null+".txt"))).thenReturn(Paths.get("src/main/resources/upload-files/"+null+".txt"));

        createFileService.store(fileMock,null,"txt");
    }

    @Test
    public void addMemWithFileWithoutType(){
        when(fileMock.getOriginalFilename()).thenReturn("testFile."+null);
        when(rootLocationMock.resolve(eq("testFile."+null))).thenReturn(Paths.get("src/main/resources/upload-files/testFile."+null));

        createFileService.store(fileMock,"testFile",null);
    }

    @Test(expected = NullPointerException.class)
    public void addMemWithFileWithNullDate() throws IOException {
        when(fileMock.getInputStream()).thenReturn(null);
        createFileService.store(fileMock,"testFile","txt");
    }
}
