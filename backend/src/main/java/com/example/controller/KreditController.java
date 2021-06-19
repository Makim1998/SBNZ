package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.KreditDTO;
import com.example.mapper.KreditMapper;
import com.example.model.Kredit;
import com.example.model.ZahtevKredit;
import com.example.service.KreditService;
import com.example.service.UserService;
import com.example.service.ZahtevService;

@RestController
@RequestMapping(value = "/krediti", produces = MediaType.APPLICATION_JSON_VALUE)
public class KreditController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private KreditService kreditService;
	
	@Autowired
	private ZahtevService zahtevService;
	
	@Autowired
	private KreditMapper kreditMapper;
	
	@GetMapping(value = "")
	public ResponseEntity<List<KreditDTO>> getAllForClient(){
		System.out.println("dobavljanje svih kredita za klijenta");
		long cid = this.userService.currentUser().getKlijent().getId();
		return new ResponseEntity<List<KreditDTO>>(this.kreditService.getForKlijent(cid).stream().map(k -> this.kreditMapper.map(k)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/{dan}")
	public ResponseEntity<Void> deleteCredit(@PathVariable long id, @PathVariable int dan){
		System.out.println("prihvatanje ponude i ugovaranje datuma");
		System.out.println(id);
		ZahtevKredit zahtev = this.zahtevService.findZahtevById(id);
		Kredit k = zahtev.getKredit();
		k.setDatumRate(dan);
		this.kreditService.save(k);
		System.out.println("uspelo");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCredit(@PathVariable long id){
		System.out.println("odbijanje ponude");
		System.out.println(id);
		ZahtevKredit zahtev = this.zahtevService.findZahtevById(id);
		long kreditId = zahtev.getKredit().getId();
		zahtev.setKredit(null);
		this.zahtevService.save(zahtev);
		System.out.println("brisanje kredita");
		this.kreditService.delete(kreditId);
		System.out.println("uspelo");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
