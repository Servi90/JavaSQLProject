package com.trifulcas.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.trifulcas.models.Dogs;
import com.trifulcas.repositories.DogsRepository;
import com.trifulcas.models.Owners;
import com.trifulcas.repositories.OwnersRepository;

@RestController
@RequestMapping("/api")
public class DogsController {
	
    @Autowired
    private DogsRepository dogsRepository;
    
    @Autowired
    private OwnersRepository ownersRepository;
    
    @GetMapping("/dogs")
    public ResponseEntity<List<Dogs>> getAllDogs() {
        List<Dogs> dogs = new ArrayList<>();
        dogsRepository.findAll().forEach(dogs::add);
        
        if (dogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/dogs/{id}")
    public ResponseEntity<Dogs> getDog(@PathVariable("id") int id) {
        Dogs dogs = dogsRepository.findById(id).orElse(null);
        
        if (dogs == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }
    
    @PostMapping("/dogs")
    public ResponseEntity<Dogs> createDog(@RequestBody Dogs dogs, @RequestParam("ownersid") Integer ownersid) {
        Owners owners = ownersRepository.findById(ownersid).orElse(null);
        if (owners != null) {
            dogs.setOwner(owners);
            Dogs newDog = dogsRepository.save(dogs);
            return new ResponseEntity<>(newDog, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/dogs/{id}")
    public ResponseEntity<Dogs> updateDog(@PathVariable("id") int id, @RequestBody Dogs dogs) {
        Dogs existingDog = dogsRepository.findById(id).orElse(null);
        
        if (existingDog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (dogs.getName() != null) {
            existingDog.setName(dogs.getName());
        }
        if (dogs.getBreed() != null) {
            existingDog.setBreed(dogs.getBreed());
        }
        if (dogs.getOwner() != null) {
            existingDog.setOwner(dogs.getOwner());
        }
        
        Dogs updatedDog = dogsRepository.save(existingDog);
        return new ResponseEntity<>(updatedDog, HttpStatus.OK);
    }

    @DeleteMapping("/dogs/{id}")
    public ResponseEntity<HttpStatus> deleteDog(@PathVariable("id") int id) {
        dogsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}