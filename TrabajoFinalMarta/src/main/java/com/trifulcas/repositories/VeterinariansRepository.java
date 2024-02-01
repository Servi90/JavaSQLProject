package com.trifulcas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trifulcas.models.Veterinarians;

public interface VeterinariansRepository extends JpaRepository<Veterinarians, Integer> {
   
}