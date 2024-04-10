package com.example.library_management_system.Exceptions;

public class PatronNotFoundException extends RuntimeException{
    public PatronNotFoundException(String message) {
        super(message);
    }
}
