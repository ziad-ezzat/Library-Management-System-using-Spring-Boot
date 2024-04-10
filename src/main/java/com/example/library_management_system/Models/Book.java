package com.example.library_management_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Length(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @Min(value = 1500 , message = "Publication year must be greater than 1500")
    @Max(value = 2024 , message = "Publication year must be less than 2024")
    private int publicationYear;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BorrowingRecord> borrowingRecords;
}
