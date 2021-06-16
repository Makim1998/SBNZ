package com.example.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.HipotekaDTO;
import com.example.model.Hipoteka;
import com.example.service.UserService;

@Component
public class HipotekaMapper {
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public Hipoteka map(HipotekaDTO hipotekaDTO) {
		Hipoteka hipoteka = new Hipoteka();
		hipoteka.setKlijent(this.userService.currentUser().getKlijent());
		hipoteka.setNekretnine(hipotekaDTO.getNekretnine());
		hipoteka.setStatus(false);
		return hipoteka;
	}
	
	@Transactional
	public HipotekaDTO map(Hipoteka hipoteka) {
		HipotekaDTO hipotekadto = new HipotekaDTO(hipoteka);
		return hipotekadto;
	}
}
