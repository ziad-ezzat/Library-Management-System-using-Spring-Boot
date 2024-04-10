package com.example.library_management_system.Repositories;

import com.example.library_management_system.Exceptions.BookNotFoundException;
import com.example.library_management_system.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b")
    List<Book> getAll();
    default Book getBookById(Long id) {
        return findById(id).orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
    }

    @Modifying
    @Transactional
    void deleteBookById(Long id);

    @Modifying
    @Transactional
    default Book addBook(String title, String author, int publicationYear, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
        book.setIsbn(isbn);
        return save(book);
    }
}
