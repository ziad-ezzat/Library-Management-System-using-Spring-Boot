package com.example.library_management_system.Controllers;

import com.example.library_management_system.Models.BorrowingRecord;
import com.example.library_management_system.Services.BorrowingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(BorrowingController.class)
public class BorrowingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingService borrowingService;

    @Test
    public void borrowBookTest() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(1L);

        when(borrowingService.borrowBook(1L, 1L)).thenReturn(record);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1}"));
    }

    @Test
    public void addReturnDateTest() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(1L);
        record.setReturnDate(new Date());

        when(borrowingService.addReturnDate(1L, 1L, record.getReturnDate())).thenReturn(record);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/borrow/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("returnDate", "2022-12-31"))
                .andExpect(status().isOk());
    }
}