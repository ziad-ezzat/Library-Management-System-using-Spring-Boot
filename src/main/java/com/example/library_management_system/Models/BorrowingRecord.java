package com.example.library_management_system.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Patron patron;

    @NotBlank(message = "Borrowing date is required")
    private Date borrowingDate;

    private Date returnDate;
}
