package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.event.WarningEvent;
import com.example.model.Kredit;
import com.example.model.ZahtevKredit;

@Service
public class KieService {
	
	private final KieContainer kieContainer;
	
	private KieSession trackingSession;
		   
	@Autowired
	private KreditService kreditService;
	
	private List<WarningEvent> processedWarnings;
	
    @Autowired
    public KieService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
        this.trackingSession = kieContainer.newKieSession();
        this.processedWarnings = new ArrayList<WarningEvent>();
    }
    
    public Object addObjectToSession(Object o, String agenda) {
	    KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(o);
        if(agenda != "") {
	    	kieSession.getAgenda().getAgendaGroup(agenda).setFocus();
	    }
        kieSession.fireAllRules();
        System.out.println(kieSession.getFactCount());
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
    
    public WarningEvent checkWarnings() {
    	WarningEvent warning = null;
    	for (Object obj : trackingSession.getObjects()) {
			if (obj instanceof WarningEvent) {
				warning = (WarningEvent) obj;
				if(this.processedWarnings.contains(warning)) {
					warning = null;
					continue;
				}
				else {
					System.out.println("pronasao warning");
					this.processedWarnings.add(warning);
					break;
				}
			}
		}
        return warning;
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
