package com.trifulcas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trifulcas.models.Owners;

public interface OwnersRepository extends JpaRepository<Owners, Integer> {
}