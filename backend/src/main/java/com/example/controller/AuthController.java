package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoginDTO;
import com.example.dto.ProfileDTO;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.security.TokenUtils;
import com.example.service.KlijentService;
import com.example.service.UserService;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("permitAll()")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private KlijentService klijentService;

	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private TokenUtils tokenUtils;
		
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping(value = "/login")
	public ResponseEntity<ProfileDTO> login(@Valid @RequestBody LoginDTO loginDTO){
		this.authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		String accessToken = this.tokenUtils.generateToken(loginDTO.getEmail());
		System.out.println(accessToken);
		User user = (User) this.userService.loadUserByUsername(loginDTO.getEmail());
		System.out.println("loged user");
		System.out.println(user.getFirstName());
		return new ResponseEntity<>(new ProfileDTO(user, accessToken), HttpStatus.OK);
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<ProfileDTO> register(@Valid @RequestBody ProfileDTO profileDTO){
		System.out.println("registracija");
		User user = (User) this.userService.loadUserByUsername(profileDTO.getEmail());
		if(user != null) {
			System.out.println("korisnicko ime zauzeto");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		User u = this.mapper.map(profileDTO);
		klijentService.save(u.getKlijent());
		userService.save(u);
		return new ResponseEntity<ProfileDTO>(profileDTO, HttpStatus.OK);
	}
		
}
