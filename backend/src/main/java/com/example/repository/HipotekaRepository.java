package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Hipoteka;

public interface HipotekaRepository extends JpaRepository<Hipoteka, Long> {
	
	
}
