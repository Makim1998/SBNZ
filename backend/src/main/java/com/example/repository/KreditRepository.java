package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Kredit;

public interface KreditRepository extends JpaRepository<Kredit, Long>{
	
	public List<Kredit> findAllByDatumRate(int datum);

	@Query("select k from Kredit k where k.klijent.id=:id")
	public List<Kredit> findByKlijentId(long id);
	
}
	