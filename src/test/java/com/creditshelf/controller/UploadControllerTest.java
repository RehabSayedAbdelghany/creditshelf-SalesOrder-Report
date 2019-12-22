package com.creditshelf.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.creditshelf.service.upload.UploadService;

import java.io.File;
import java.io.FileInputStream;


@RunWith(SpringRunner.class)
@WebMvcTest(UploadController.class)
public class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UploadService uploadService;

    private static final String fileBasePath = "src/test/files/";



    @Test
    public void testUploadFile_ProductFile() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "Capsule Corporation.csv",
                "multipart/form-data",
                new FileInputStream(new File(fileBasePath + "Capsule Corporation.csv"))
        );

        given(uploadService.uploadProducts(file)).willReturn(4);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/product")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response",is("File Uploaded Successfully")));
    }
    
    
    @Test
    public void testUploadFile_SalesFile() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "Capsule Corporation.csv",
                "multipart/form-data",
                new FileInputStream(new File(fileBasePath + "Sales.csv"))
        );

        given(uploadService.uploadProducts(file)).willReturn(45);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/sales")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response",is("File Uploaded Successfully")));
    }
}
