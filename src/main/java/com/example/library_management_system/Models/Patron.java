package com.example.library_management_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter
@Setter
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Length(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotBlank(message = "Contact information is required")
    @Length(min = 11, max = 11, message = "Contact information must be 11 characters long")
    @Column(unique = true)
    private String contactInformation;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BorrowingRecord> borrowingRecords;
}
