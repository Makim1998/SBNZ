package com.example.service;

import java.util.List;

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
        this.trackingSession = kieContainer.newKieSession();
    }
    
    public Object addObjectToSession(Object o, String agenda) {
	    KieSession kieSession = kieContainer.newKieSession();
	    if(agenda != "") {
	    	kieSession.getAgenda().getAgendaGroup(agenda).setFocus();
	    }
        kieSession.insert(o);
        kieSession.fireAllRules();
        kieSession.dispose();
        return o;
    }
    
    public Object addObjectToTrackingSession(Object o) {
    	this.trackingSession.getAgenda().getAgendaGroup("cep").setFocus();
        trackingSession.insert(o);
        trackingSession.fireAllRules();
        System.out.println(trackingSession.getFactCount());
        return o;
    }
    
    @Scheduled(cron="0 0 11 * * ?")
    public List<Kredit> addCreditstoTrackingSession() {
    	trackingSession = this.kieContainer.newKieSession();
        trackingSession.getAgenda().getAgendaGroup("mesecna-isplata").setFocus();
    	System.out.println("poziv metode za dnevnu proveru skidanja mesecne rate kredita");
    	List<Kredit> krediti = this.kreditService.findAllByDayOfPay();
    	for (Kredit k: krediti ) {
    		this.trackingSession.insert(k);
    	}
    	trackingSession.fireAllRules();
		return krediti;
    }
    
}
