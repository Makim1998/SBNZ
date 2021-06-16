package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Transakcija;

public interface TransakcijaRepository extends JpaRepository<Transakcija, Long> {
	
	@Query("select t from Transakcija t where t.klijent.id=:id")
	public List<Transakcija> findByKlijentId(long id);
	
}
