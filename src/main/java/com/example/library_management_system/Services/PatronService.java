package com.example.library_management_system.Services;

import com.example.library_management_system.Models.Patron;
import com.example.library_management_system.Repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public void addPatron(Patron patron) {
        patronRepository.addPatron(patron.getName(), patron.getContactInformation());
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.getAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.getPatronById(id);
    }

    public void deletePatronById(Long id) {
        patronRepository.deletePatronById(id);
    }

    public void updatePatron(Patron patron, Long id) {

        if (patron == null) {
            throw new RuntimeException("Patron not found");
        }

        Patron existingPatron = patronRepository.getPatronById(id);

        if (patron.getName() != null) {
            existingPatron.setName(patron.getName());
        }

        if (patron.getContactInformation() != null) {
            existingPatron.setContactInformation(patron.getContactInformation());
        }

        patronRepository.save(existingPatron);
    }
}
