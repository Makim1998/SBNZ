package com.example.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PonudaDTO;
import com.example.dto.ZahtevDTO;
import com.example.mapper.ZahtevMapper;
import com.example.model.Klijent;
import com.example.model.Kredit;
import com.example.model.ZahtevKredit;
import com.example.service.KieService;
import com.example.service.KreditService;
import com.example.service.UserService;
import com.example.service.ZahtevService;

@RestController
@RequestMapping(value = "/zahtevi", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZahtevController {
	
	@Autowired
	private ZahtevService zahtevService;
	
	@Autowired
	private KreditService kreditService;
	
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
		Klijent k = this.userService.currentUser().getKlijent();
		System.out.println("ne znam sta se desava zemlja je tako daleka");
		System.out.println(k.getKrediti().size());
		if(k.getKrediti().size() == 0) {
			System.out.println("krediti nisu inicijalizovani");
			List<Kredit> krediti = new ArrayList<Kredit>();
			k.setKrediti(krediti);
		}
		zk.setKlijent(this.userService.currentUser().getKlijent());
		zk = (ZahtevKredit) this.kieService.addObjectToSession(zk, "zahtev");
		System.out.println(zk.getOdgovor());
		zk = this.zahtevService.save(zk);
		return new ResponseEntity<>(zahtevMapper.map(zk), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<PonudaDTO> kredit(@Valid @PathVariable long id){
		System.out.println("racunanje osnovne kamate");
		ZahtevKredit z = this.zahtevService.findZahtevById(id);
		Kredit k = new Kredit();
		k.setKamata(0);
		k.setKlijent(z.getKlijent());
		k.setMesecna_rata(0);
		k.setZahtev(z);
		z.setKredit(k);
		z = (ZahtevKredit) this.kieService.addObjectToSession(z, "osnovnaKamata");
		System.out.println(z.getKlijent().getNagradni_poeni());
		System.out.println(z.getKredit().getKamata());
		System.out.println(z.getKredit().getMesecna_rata());
		System.out.println(z.isStatus());
		if(!z.isStatus()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		z.getKredit().setDatumUgovora(new Date());
		z.getKredit().setDatumRate(15);
		this.kreditService.save(z.getKredit());
		this.zahtevService.save(z);
		PonudaDTO p = new PonudaDTO(id, 15, z.getKredit().getKamata(), z.getKredit().getMesecna_rata());
		return new ResponseEntity<PonudaDTO>(p, HttpStatus.OK);
	}
	
}
