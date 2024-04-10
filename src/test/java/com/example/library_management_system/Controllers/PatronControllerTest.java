package com.example.library_management_system.Controllers;

import com.example.library_management_system.Exceptions.PatronNotFoundException;
import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Services.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PatronController.class)
public class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    private Patron patron1;
    private Patron patron2;

    @BeforeEach
    public void setup() {
        patron1 = new Patron();
        patron1.setName("John Doe");
        patron1.setContactInformation("01554538516");

        patron2 = new Patron();
        patron2.setName("Jane Doe");
        patron2.setContactInformation("01554538516");
    }

    @Test
    public void getAllPatronsTest() throws Exception {
        List<Patron> patrons = Arrays.asList(patron1, patron2);

        when(patronService.getAllPatrons()).thenReturn(patrons);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name': 'John Doe', 'contactInformation': '01554538516'}, {'name': 'Jane Doe', 'contactInformation': '01554538516'}]"));
    }

    @Test
    public void getPatronByIdTest() throws Exception {
        when(patronService.getPatronById(1L)).thenReturn(patron1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'name': 'John Doe', 'contactInformation': '01554538516'}"));
    }

    @Test
    public void getPatronByIdNotFoundTest() throws Exception {
        when(patronService.getPatronById(1L)).thenThrow(new PatronNotFoundException("Patron with id 1 not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        Mockito.verify(patronService).getPatronById(1L);
    }

    @Test
    public void updatePatronTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"contactInformation\": \"01554538516\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void addPatronTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"contactInformation\": \"01554538516\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePatronByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}