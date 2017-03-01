package edu.pl.pollub.service;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.exception.StorageException;
import edu.pl.pollub.repository.MemRepository;
import edu.pl.pollub.service.implementation.MemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by Dell on 2017-02-28.
 */
public class MemServiceTest {

    private MemService createMemService;
    private MemRepository memRepositoryMock;
    private FileService fileServiceMock;

    @Before
    public void setUp() {
        memRepositoryMock = Mockito.mock(MemRepository.class);
        fileServiceMock = Mockito.mock(FileService.class);
        createMemService = new MemServiceImpl(memRepositoryMock,fileServiceMock);
    }

    @Test
    public void addMemTest(){
        MultipartFile file=Mockito.mock(MultipartFile.class);

        when(file.getContentType()).thenReturn("jpg");
        doAnswer(returnsFirstArg()).when(memRepositoryMock).save(any(Mem.class));
        doNothing().when(fileServiceMock).store(any(MultipartFile.class),any(String.class),any(String.class));

        Mem mem = createMemService.addMem(file,"testMem");
        assertEquals("testMem", mem.getTitle());
        assertNotNull(mem.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void showMemesFromPageWithInvalidNumberTest() throws Exception{
        createMemService.showMemesFromPage(-5);
    }





}
