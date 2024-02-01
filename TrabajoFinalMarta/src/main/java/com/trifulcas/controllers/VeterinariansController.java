package com.trifulcas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trifulcas.models.Veterinarians;
import com.trifulcas.repositories.VeterinariansRepository;

@RestController
@RequestMapping("/api")
public class VeterinariansController {

    @Autowired
    private VeterinariansRepository veterinariansRepository;

    @GetMapping("/veterinarians")
    public ResponseEntity<List<Veterinarians>> getAllVeterinarians() {
        List<Veterinarians> veterinarians = new ArrayList<>();
        veterinariansRepository.findAll().forEach(veterinarians::add);
        
        if (veterinarians.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(veterinarians, HttpStatus.OK);
    }

    @GetMapping("/veterinarians/{id}")
    public ResponseEntity<Veterinarians> getVeterinarian(@PathVariable("id") int id) {
        Veterinarians veterinarians = veterinariansRepository.findById(id).orElse(null);
        
        if (veterinarians == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(veterinarians, HttpStatus.OK);
    }

    @PostMapping("/veterinarians")
    public ResponseEntity<Veterinarians> createVeterinarian(@RequestBody Veterinarians veterinarians) {
        Veterinarians savedVeterinarian = veterinariansRepository.save(veterinarians);
        return new ResponseEntity<>(savedVeterinarian, HttpStatus.CREATED);
    }

    @PutMapping("/veterinarians/{id}")
    public ResponseEntity<Veterinarians> updateVeterinarian(@PathVariable("id") int id, @RequestBody Veterinarians veterinarians) {
        Veterinarians existingVeterinarian = veterinariansRepository.findById(id).orElse(null);
        
        if (existingVeterinarian == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        existingVeterinarian.setName(veterinarians.getName());
        existingVeterinarian.setSpecialty(veterinarians.getSpecialty());
        
        return new ResponseEntity<>(veterinariansRepository.save(existingVeterinarian), HttpStatus.OK);
    }

    @DeleteMapping("/veterinarians/{id}")
    public ResponseEntity<HttpStatus> deleteVeterinarian(@PathVariable("id") int id) {
        veterinariansRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

