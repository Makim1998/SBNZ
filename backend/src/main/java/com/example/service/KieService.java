package com.example.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.model.Kredit;

@Service
public class KieService {
	
	private final KieContainer kieContainer;
	
	private KieSession trackingSession;
	   
	@Autowired
	private KreditService kreditService;
	
    @Autowired
    public KieService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
    
    public Object addObjectToSession(Object o) {
	    KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(o);
        kieSession.fireAllRules();
        kieSession.dispose();
        return o;
    }
    
    @Scheduled(cron="0 0 11 * * ?")
    public void addCreditstoTrackingSession() {
        this.trackingSession = kieContainer.newKieSession();
        trackingSession.getAgenda().getAgendaGroup("mesecna-isplata").setFocus();
    	System.out.println("poziv metode za dnevnu proveru skidanja mesecne rate kredita");
    	for (Kredit k: this.kreditService.findAllByDayOfPay()) {
    		this.trackingSession.insert(k);
    	}
    	trackingSession.fireAllRules();
    	trackingSession.dispose();
    }
}
