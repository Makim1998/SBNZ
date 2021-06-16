package com.example.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProfileDTO;
import com.example.model.Authority;
import com.example.model.Klijent;
import com.example.model.User;

@Component
public class UserMapper {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public User map(ProfileDTO profileDTO) {
		User user = new User();
		user.setEmail(profileDTO.getEmail());
		user.setPassword(this.passwordEncoder.encode(profileDTO.getPassword()));
		user.setFirstName(profileDTO.getFirstName());
		user.setLastName(profileDTO.getLastName());
		Authority authority = new Authority();
		authority.setId(2L);
		authority.setName("client");
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(authority);
		user.setAuthorities(authorities);
		Klijent klijent = new Klijent(profileDTO.getGodine(), profileDTO.getMesecna_zarada());
		user.setKlijent(klijent);
		return user;
	}
	
	@Transactional
	public ProfileDTO map(User user) {
		ProfileDTO profile = new ProfileDTO();
		profile.setId(user.getId());
		Klijent k = user.getKlijent();
		if (k != null) {
			profile.setMesecna_zarada(k.getMesecna_zarada());
			profile.setGodine(k.getGodine());
		}
		profile.setFirstName(user.getFirstName());
		profile.setLastName(user.getLastName());
		profile.setEmail(user.getEmail());
		profile.setPassword(user.getPassword());
		return profile;
	}
	
}
