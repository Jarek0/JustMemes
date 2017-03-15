package edu.pl.pollub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pl.pollub.controller.restController.MemController;
import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.service.MemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Dell on 2017-03-03.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MemController.class)
public class MemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMultipartFile mockfile;

    @MockBean
    private MemService memServiceMock;

    @Before
    public void setup() {
        this.objectMapper=new ObjectMapper();
        this.mockfile = new MockMultipartFile("file", "myFile.txt", "text/plain", "hello".getBytes());
    }

    @Test
    public void addMemSuccessfully() throws Exception {
        when(memServiceMock.addMem(eq(mockfile),eq("myFile"))).thenReturn(new Mem("myFile","txt",new Timestamp(System.currentTimeMillis()),null));


        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/mem")
                .file(mockfile)
                .param("memTitle","myFile"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("myFile")))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

}
