package com.example.library_management_system.Repositories;

import com.example.library_management_system.Models.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingRecord, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = ?1 AND br.patron.id = ?2 AND br.returnDate IS NULL")
    BorrowingRecord getBorrowingRecordByBookIdAndPatronId(Long bookId, Long patronId);
}
