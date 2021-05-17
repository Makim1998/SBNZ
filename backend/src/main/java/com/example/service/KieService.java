package com.example.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KieService {
	
	private final KieContainer kieContainer;
	   
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
}
