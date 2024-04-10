package com.example.library_management_system.Controllers;

import com.example.library_management_system.Models.BorrowingRecord;
import com.example.library_management_system.Services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public BorrowingRecord borrowBook(@PathVariable Long bookId,@PathVariable Long patronId) {
        return borrowingService.borrowBook(bookId, patronId);
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public BorrowingRecord addReturnDate(@PathVariable Long bookId,@PathVariable Long patronId,@DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate) {
        return borrowingService.addReturnDate(bookId, patronId, returnDate);
    }
}
