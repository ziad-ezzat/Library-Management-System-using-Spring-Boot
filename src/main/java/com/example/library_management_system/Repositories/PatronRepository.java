package com.example.library_management_system.Repositories;

import com.example.library_management_system.Exceptions.PatronNotFoundException;
import com.example.library_management_system.Models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Patron p")
    List<Patron> getAll();

    @Transactional(readOnly = true)
    default Patron getPatronById(Long id) {
        return findById(id).orElseThrow(() -> new PatronNotFoundException("Patron with id " + id + " not found"));
    }

    @Modifying
    @Transactional
    void deletePatronById(Long id);

    @Modifying
    @Transactional
    default Patron addPatron(String name, String contactInformation) {
        Patron patron = new Patron();
        patron.setName(name);
        patron.setContactInformation(contactInformation);
        return save(patron);
    }
}
