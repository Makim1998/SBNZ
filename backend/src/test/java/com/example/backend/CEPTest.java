package com.example.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.event.TransactionEvent;
import com.example.model.Klijent;
import com.example.model.Kredit;
import com.example.model.TipTransakcije;
import com.example.service.KieService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CEPTest {

	@Autowired
	private KieService kieService;
	
	private List<TransactionEvent> transactionEvents = new ArrayList<TransactionEvent>();
	
	@Before
	public void init() {
		Klijent k = new Klijent();
		k.setId(2L);
		k.setGodine(50);
		k.setMesecna_zarada(2500);
		k.setNagradni_poeni(0);
		k.setStanjeRacun(2000);
		
		
		TransactionEvent t = new TransactionEvent(k, 600.0, TipTransakcije.ISPLATA);

		TransactionEvent t1 = new TransactionEvent(k, 1500.0, TipTransakcije.ISPLATA);

		TransactionEvent t2 = new TransactionEvent(k, 600.0, TipTransakcije.ISPLATA);

		this.transactionEvents.add(t);
		this.transactionEvents.add(t1);
		this.transactionEvents.add(t2);
	}
	
	@Test
	public void testMesecnaRataUspela() {
		//List<Kredit> krediti = this.kieService.addCreditstoTrackingSession();
		//assertEquals(1, krediti.size());
		//assertEquals(4700, krediti.get(0).getKlijent().getStanjeRacun(), 0.0001);
	}
	
	@Test
	public void testMesecnaRataNeuspela() {
		// nezdodno za mockovati jer metoda direktno iz baze vuce kredite koji imaju dogovoren danasnji dan a i zbog hrono izraza
		//List<Kredit> krediti = this.kieService.addCreditstoTrackingSession();
		//krediti.get(0).getKlijent().setStanjeRacun(200);
		//assertEquals(1, krediti.size());
		//assertEquals(200, krediti.get(0).getKlijent().getStanjeRacun(), 0.0001);
	}
	
	
	@Test
	public void test3TransakcijePojedinacno() {
		this.transactionEvents.get(1).setTotalAmount(550.0);
		TransactionEvent t1  = (TransactionEvent) this.kieService.addObjectToTrackingSession(this.transactionEvents.get(0));

		assertEquals(0, t1.getClient().getNagradni_poeni());
		TransactionEvent t2  = (TransactionEvent) this.kieService.addObjectToTrackingSession(this.transactionEvents.get(1));

		assertEquals(0, t2.getClient().getNagradni_poeni());
		this.kieService.addObjectToTrackingSession(this.transactionEvents.get(2));

		assertNotNull(this.kieService.checkWarnings());
	}
	
	@Test
	public void test3TransakcijeSuma() {
		this.transactionEvents.get(0).setTotalAmount(400.0);
		this.transactionEvents.get(1).setTotalAmount(1000.0);
		this.transactionEvents.get(2).setTotalAmount(1600.0);
		TransactionEvent t1  = (TransactionEvent) this.kieService.addObjectToTrackingSession(this.transactionEvents.get(0));
		assertEquals(0, t1.getClient().getNagradni_poeni());
		TransactionEvent t2  = (TransactionEvent) this.kieService.addObjectToTrackingSession(this.transactionEvents.get(1));
		assertEquals(0, t2.getClient().getNagradni_poeni());
		this.kieService.addObjectToTrackingSession(this.transactionEvents.get(2));
		assertNotNull(this.kieService.checkWarnings());
	}
	
}
