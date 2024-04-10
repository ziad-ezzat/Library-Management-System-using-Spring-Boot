package com.example.library_management_system.Controllers;

import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooksTest() throws Exception {
        Book book1 = new Book();
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setTitle("Book 2");

        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'title': 'Book 1'}, {'title': 'Book 2'}]"));
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Book book = new Book();
        book.setTitle("Book 1");

        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'title': 'Book 1'}"));
    }

    @Test
    public void deleteBookByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}