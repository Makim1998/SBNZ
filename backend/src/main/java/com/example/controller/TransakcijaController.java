package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TransakcijaDTO;
import com.example.mapper.TransakcijaMapper;
import com.example.model.TipTransakcije;
import com.example.model.Transakcija;
import com.example.service.KieService;
import com.example.service.TransakcijaService;
import com.example.service.UserService;

@RestController
@RequestMapping(value = "/transakcije", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransakcijaController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KieService kieService;
	
	@Autowired
	private TransakcijaService transakcijaService;
	
	@Autowired
	private TransakcijaMapper transakcijaMapper;
	
	@GetMapping(value = "")
	public ResponseEntity<List<TransakcijaDTO>> getAllForClient(){
		System.out.println("dobavljanje svih transakcija za klijenta");
		long cid = this.userService.currentUser().getKlijent().getId();
		return new ResponseEntity<List<TransakcijaDTO>>(this.transakcijaService.getForClient(cid).stream().map(t -> this.transakcijaMapper.map(t)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<TransakcijaDTO> transakcija(@RequestBody TransakcijaDTO transakcija){
		System.out.println("nova transakcija");
		Transakcija t = this.transakcijaMapper.map(transakcija);
		if(t.getTip() == TipTransakcije.UPLATA) {
			t.getKlijent().setStanjeRacun(t.getKlijent().getStanjeRacun() + t.getIznos());
		}
		else if(t.getKlijent().getStanjeRacun() <= t.getIznos()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		else {
			t.getKlijent().setStanjeRacun(t.getKlijent().getStanjeRacun() - t.getIznos());
		}
		this.transakcijaService.save(t);
		t = (Transakcija) this.kieService.addObjectToSession(t, "cep");
		return new ResponseEntity<TransakcijaDTO>(this.transakcijaMapper.map(t), HttpStatus.OK);
	}

	
}
