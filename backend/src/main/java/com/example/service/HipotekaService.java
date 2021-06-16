package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Hipoteka;
import com.example.repository.HipotekaRepository;

@Service
public class HipotekaService {
	
	@Autowired
	private HipotekaRepository hipotekaRepository;
	
	@Transactional(readOnly = true)
	public List<Hipoteka> findAll(){
		return this.hipotekaRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Hipoteka save(Hipoteka hipoteka) {
		return this.hipotekaRepository.save(hipoteka);
	}

}
