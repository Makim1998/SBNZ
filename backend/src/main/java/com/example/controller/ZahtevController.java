package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ZahtevDTO;
import com.example.mapper.ZahtevMapper;
import com.example.model.ZahtevKredit;
import com.example.service.KieService;
import com.example.service.UserService;
import com.example.service.ZahtevService;

@RestController
@RequestMapping(value = "/zahtevi", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZahtevController {
	
	@Autowired
	private ZahtevService zahtevService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ZahtevMapper zahtevMapper;
	
	@Autowired
	private KieService kieService;
	
	@PostMapping(value = "")
	public ResponseEntity<ZahtevDTO> zahtev(@Valid @RequestBody ZahtevDTO zahtevDTO){
		System.out.println("zahtev za kredit");
		ZahtevKredit zk = zahtevMapper.map(zahtevDTO);
		zk.setStatus(true);
		zk.setOdgovor("Zahtev uspeo.");
		if (this.userService.currentUser().getKlijent() != null) {
			zk.setKlijent(this.userService.currentUser().getKlijent());
		}
		zk = (ZahtevKredit) this.kieService.addObjectToSession(zk);
		System.out.println(zk.getOdgovor());
		this.zahtevService.save(zk);
		return new ResponseEntity<>(zahtevMapper.map(zk), HttpStatus.OK);
	}
}
