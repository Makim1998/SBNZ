package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Klijent;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent, Long>{

}
