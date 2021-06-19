package com.example.mapper;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.KreditDTO;
import com.example.model.Kredit;

@Component
public class KreditMapper {
	
	@Transactional
	public KreditDTO map(Kredit kredit) {
		KreditDTO dto = new KreditDTO(kredit);
		return dto;
	}

}
