package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Transakcija;
import com.example.repository.TransakcijaRepository;

@Service
public class TransakcijaService {
	
	@Autowired
	private TransakcijaRepository transakcijaRepository;
	
	@Transactional(readOnly = false)
	public Transakcija save(Transakcija t) {
		return this.transakcijaRepository.save(t);
	}

	@Transactional(readOnly = true)
	public List<Transakcija> getForClient(long id) {
		return this.transakcijaRepository.findByKlijentId(id);
	}
	
}
