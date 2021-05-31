package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Kredit;

public interface KreditRepository extends JpaRepository<Kredit, Long>{
	
	public List<Kredit> findAllByDatumRate(int datum);
	
}
	