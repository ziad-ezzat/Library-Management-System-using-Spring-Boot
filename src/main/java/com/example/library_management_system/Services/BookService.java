package com.example.library_management_system.Services;

import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.addBook(book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getIsbn());
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteBookById(id);
    }

    public void updateBook(Book book, Long id) {

        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        Book existingBook = bookRepository.getBookById(id);

        if (book.getTitle() != null) {
            existingBook.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (book.getPublicationYear() != 0) {
            existingBook.setPublicationYear(book.getPublicationYear());
        }
        if (book.getIsbn() != null) {
            existingBook.setIsbn(book.getIsbn());
        }

        bookRepository.save(existingBook);
    }
}
