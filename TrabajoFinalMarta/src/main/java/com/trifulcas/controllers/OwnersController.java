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

import com.trifulcas.models.Owners;
import com.trifulcas.repositories.OwnersRepository;
import com.trifulcas.models.Dogs;
import com.trifulcas.repositories.DogsRepository;

@RestController
@RequestMapping("/api")
public class OwnersController {

    @Autowired
    private OwnersRepository ownersRepository;
    
    @Autowired
    private DogsRepository dogsRepository;

    @GetMapping("/owners")
    public ResponseEntity<List<Owners>> getAllOwners() {
        List<Owners> owners = new ArrayList<>();
        ownersRepository.findAll().forEach(owners::add);
        
        if (owners.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<Owners> getOwner(@PathVariable("id") int id) {
        Owners owners = ownersRepository.findById(id).orElse(null);
        
        if (owners == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PostMapping("/{ownersid}/dogs")
    public ResponseEntity<Dogs> addDogToOwner(@PathVariable Integer ownerId, @RequestBody Dogs dog) {
        Owners owner = ownersRepository.findById(ownerId).orElse(null);

        if (owner != null) {
            dog.setOwner(owner);
            Dogs newDog = dogsRepository.save(dog);
            return new ResponseEntity<>(newDog, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/owners")
    public ResponseEntity<Owners> createOwner(@RequestBody Owners owners) {
        Owners savedOwner = ownersRepository.save(owners);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }
    

    @PutMapping("/owners/{id}")
    public ResponseEntity<Owners> updateOwner(@PathVariable("id") int id, @RequestBody Owners owners) {
        Owners existingOwner = ownersRepository.findById(id).orElse(null);
        
        if (existingOwner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        existingOwner.setName(owners.getName());
        existingOwner.setAddress(owners.getAddress());
        
        return new ResponseEntity<>(ownersRepository.save(existingOwner), HttpStatus.OK);
    }

    @DeleteMapping("/owners/{id}")
    public ResponseEntity<HttpStatus> deleteOwner(@PathVariable("id") int id) {
        ownersRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}