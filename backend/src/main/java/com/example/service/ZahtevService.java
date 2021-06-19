package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.ZahtevKredit;
import com.example.repository.ZahtevKreditRepository;

@Service
@Transactional(readOnly = true)
public class ZahtevService {
	
	@Autowired
	private ZahtevKreditRepository zahtevRepository;
		
	@Transactional(readOnly = true)
	public Optional<ZahtevKredit> loadByUserId(Long id) {
		return this.zahtevRepository.findById(id);	
	}
	
	@Transactional(readOnly = false)
	public ZahtevKredit save(ZahtevKredit zahtev) {
		return this.zahtevRepository.save(zahtev);
	}
	
	@Transactional(readOnly = false)
	public void delete(long id) {
		this.zahtevRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public ZahtevKredit findZahtevById(long id) {
		if(this.zahtevRepository.findById(id).isPresent()){
			return this.zahtevRepository.findById(id).get();
		}
		return null;
	}
							
}
