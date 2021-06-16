package com.example.mapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.TransakcijaDTO;
import com.example.model.TipTransakcije;
import com.example.model.Transakcija;
import com.example.service.UserService;

@Component
public class TransakcijaMapper {
	
	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	public Transakcija map(TransakcijaDTO transakcijaDTO) {
		Transakcija t = new Transakcija();
		t.setKlijent(this.userService.currentUser().getKlijent());
		t.setDatum(new Date());
		t.setIznos(transakcijaDTO.getIznos());
		if(transakcijaDTO.getTip().equals("ISPLATA")) {
			t.setTip(TipTransakcije.ISPLATA);
		}
		else {
			t.setTip(TipTransakcije.UPLATA);
		}
		return t;
	}
	
	@Transactional
	public TransakcijaDTO map(Transakcija transakcija) {
		TransakcijaDTO dto = new TransakcijaDTO(transakcija);
		return dto;
	}
}
