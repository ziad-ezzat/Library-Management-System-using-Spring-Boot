package com.example.library_management_system.Services;

import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Models.BorrowingRecord;
import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Repositories.BookRepository;
import com.example.library_management_system.Repositories.BorrowingRepository;
import com.example.library_management_system.Repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;

    public BorrowingRecord addReturnDate(Long bookId, Long patronId, Date returnDate) {
        Optional<BorrowingRecord> optionalBorrowingRecord = Optional.ofNullable(borrowingRepository.getBorrowingRecordByBookIdAndPatronId(bookId, patronId));
        if (optionalBorrowingRecord.isPresent()) {
            BorrowingRecord borrowingRecord = optionalBorrowingRecord.get();
            borrowingRecord.setReturnDate(returnDate);
            return borrowingRepository.save(borrowingRecord);
        } else {
            throw new RuntimeException("No borrowing record found for the given book and patron");
        }
    }

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.getBookById(bookId);
        Patron patron = patronRepository.getPatronById(patronId);
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(new Date());
        return borrowingRepository.save(borrowingRecord);
    }
}
