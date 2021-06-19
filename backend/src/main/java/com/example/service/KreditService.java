package com.example.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Kredit;
import com.example.repository.KreditRepository;

@Service
@Transactional(readOnly = true)
public class KreditService {
	
	@Autowired
	private KreditRepository kreditRepository;
	
	@Transactional(readOnly = true)
	public List<Kredit> findAll(){
		return this.kreditRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Kredit> findAllByDayOfPay(){
        Date currentDate=new Date(); //current date
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);   
		return this.kreditRepository.findAllByDatumRate(cal.get(Calendar.DAY_OF_MONTH));
	}
	
	@Transactional(readOnly = false)
	public Kredit save(Kredit k){
		return this.kreditRepository.save(k);
	}
	
	@Transactional(readOnly = true)
	public List<Kredit> getForKlijent(long id){
		return this.kreditRepository.findByKlijentId(id);
	}

	public void delete(Kredit kredit) {
		this.kreditRepository.delete(kredit);
	}

}
