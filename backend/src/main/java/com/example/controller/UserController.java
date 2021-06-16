package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProfileDTO;
import com.example.dto.ZahtevDTO;
import com.example.dto.ZirantDTO;
import com.example.mapper.ZahtevMapper;
import com.example.model.Klijent;
import com.example.model.ZahtevKredit;
import com.example.service.KieService;
import com.example.service.UserService;
import com.example.service.ZahtevService;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KieService kieService;
	
	@Autowired
	private ZahtevService zahtevService;
	
	@Autowired
	private ZahtevMapper zahtevMapper;
	
	@GetMapping(value = "")
	public ResponseEntity<List<ProfileDTO>> getAllClients(){
		System.out.println("dobavljanje svih mogucih ziranata");
		long id = this.userService.currentUser().getId();
		return new ResponseEntity<List<ProfileDTO>>(this.userService.getAllZirants(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<ZahtevDTO> zirant(@RequestBody ZirantDTO zirant){
		System.out.println("provera pravila za ziranta");
		if(this.userService.findUserById(zirant.getZirant()) == null) {
			System.out.println("null nema ziranta");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		if(this.userService.findUserById(zirant.getZirant()).getKlijent() == null) {
			System.out.println("null nema klijenta");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Klijent k = this.userService.findUserById(zirant.getZirant()).getKlijent();
		ZahtevKredit z = this.zahtevService.findZahtevById(zirant.getZahtev());
		z.setKlijent(k);
		z = (ZahtevKredit) this.kieService.addObjectToSession(z, "zahtev");
		Klijent praviKlijent = this.userService.currentUser().getKlijent();
		z.setKlijent(praviKlijent);
		System.out.println(z.isStatus());
		this.zahtevService.save(z);
		return new ResponseEntity<ZahtevDTO>(this.zahtevMapper.map(z), HttpStatus.OK);
	}
	
}
