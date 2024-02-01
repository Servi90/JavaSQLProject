package com.trifulcas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trifulcas.models.Dogs;

public interface DogsRepository extends JpaRepository <Dogs, Integer> {
	List<Dogs> findByNameContaining(String name);
	
}
