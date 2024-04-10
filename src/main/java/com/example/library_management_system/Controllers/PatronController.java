package com.example.library_management_system.Controllers;

import com.example.library_management_system.Exceptions.BookNotFoundException;
import com.example.library_management_system.Exceptions.PatronNotFoundException;
import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Services.PatronService;
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
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping()
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public Patron getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id);
    }

    @PutMapping("/{id}")
    public void updatePatron(@Valid @RequestBody Patron patron, @PathVariable Long id) {
        patronService.updatePatron(patron, id);
    }

    @PostMapping()
    public void addPatron(@Valid @RequestBody Patron patron) {
        patronService.addPatron(patron);
    }

    @DeleteMapping("/{id}")
    public void deletePatronById(@PathVariable Long id) {
        patronService.deletePatronById(id);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PatronNotFoundException.class)
    public Map<String, String> handleBookNotFoundException(
            PatronNotFoundException ex) {
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
