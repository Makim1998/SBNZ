package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Klijent;
import com.example.repository.KlijentRepository;

@Service
public class KlijentService {
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@Transactional(readOnly = false)
	public Klijent save(Klijent k) {
		return this.klijentRepository.save(k);
	}

}
