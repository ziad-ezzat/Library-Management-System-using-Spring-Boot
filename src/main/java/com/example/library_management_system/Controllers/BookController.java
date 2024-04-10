package com.example.library_management_system.Controllers;

import com.example.library_management_system.Exceptions.BookNotFoundException;
import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Services.BookService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping()
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@Valid @RequestBody Book book, @PathVariable Long id) {
        bookService.updateBook(book, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public Map<String, String> handleBookNotFoundException(
            BookNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }


    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map<String, String> handleMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }
}
