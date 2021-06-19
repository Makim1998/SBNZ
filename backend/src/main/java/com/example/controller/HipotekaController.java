package com.example.controller;

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

import com.example.dto.HipotekaDTO;
import com.example.dto.ZahtevDTO;
import com.example.mapper.HipotekaMapper;
import com.example.mapper.ZahtevMapper;
import com.example.model.Hipoteka;
import com.example.model.ZahtevKredit;
import com.example.service.HipotekaService;
import com.example.service.KieService;
import com.example.service.ZahtevService;

@RestController
@RequestMapping(value = "/hipoteke", produces = MediaType.APPLICATION_JSON_VALUE)
public class HipotekaController {
	
	@Autowired
	private HipotekaService hipotekaService;
	
	@Autowired
	private ZahtevService zahtevService;
	
	@Autowired
	private KieService kieService;
	
	@Autowired
	private HipotekaMapper hipotekaMapper;
	
	@Autowired
	private ZahtevMapper zahtevMapper;
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<ZahtevDTO> hipoteka(@PathVariable long id, @Valid @RequestBody HipotekaDTO hipotekaDTO){
		System.out.println("hipoteka");
		Hipoteka h = hipotekaMapper.map(hipotekaDTO);
		ZahtevKredit z = this.zahtevService.findZahtevById(id);
		Hipoteka stara = z.getKlijent().getHipoteka();
		z.getKlijent().setHipoteka(h);
		z = (ZahtevKredit) this.kieService.addObjectToSession(z,"hipoteka");
		System.out.println(z.isStatus());
		if(!z.isStatus()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		if(stara != null) {
			System.out.println("Klijent vec ima hipoteku");
		}
		else {
			System.out.println("Prva hipoteka za klijenta");
			this.hipotekaService.save(h);
		}
		return new ResponseEntity<ZahtevDTO>(zahtevMapper.map(z), HttpStatus.OK);
	}

}
