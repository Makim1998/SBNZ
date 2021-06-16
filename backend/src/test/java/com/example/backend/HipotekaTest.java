package com.example.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Hipoteka;
import com.example.model.Klijent;
import com.example.model.Nekretnina;
import com.example.model.TipGarancije;
import com.example.model.TipKredita;
import com.example.model.TipNekretnine;
import com.example.model.ZahtevKredit;
import com.example.model.ZonaNekretnine;
import com.example.service.KieService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HipotekaTest {
	
	@Autowired
	private KieService kieService;
	
	private ZahtevKredit zahtev;
	
	@Before
	public void init() {
		Hipoteka h = new Hipoteka();
		List<Nekretnina> nekretnine = new ArrayList<Nekretnina>();
		
		Nekretnina n1 = new Nekretnina();
		n1.setZona(ZonaNekretnine.I);
		n1.setTip(TipNekretnine.STAN);
		n1.setKvadratura(40);
		n1.setProcenjenaVrednost(0);
		nekretnine.add(n1);
		
		h.setNekretnine(nekretnine);
		
		ZahtevKredit z = new ZahtevKredit();
		z.setIznos(80000);
		z.setPeriod(120);
		z.setTipGarancije(TipGarancije.HIPOTEKA);
		z.setTipKredita(TipKredita.STAMBENI);
		
		Klijent k = new Klijent();
		k.setGodine(50);
		k.setMesecna_zarada(2500);
		
		z.setKlijent(k);
		k.setHipoteka(h);
		this.zahtev = z;
	}
	
	@Test
	public void testHipotekaOdbijena() {
		List<Nekretnina> obradjeneneNekretnine = new ArrayList<Nekretnina>();
		for (Nekretnina n: this.zahtev.getKlijent().getHipoteka().getNekretnine()) {
			Nekretnina obradjena = (Nekretnina) this.kieService.addObjectToSession(n, "hipoteka");
			obradjeneneNekretnine.add(obradjena);
		}
		this.zahtev.getKlijent().getHipoteka().setNekretnine(obradjeneneNekretnine);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "hipoteka");
		assertEquals("Procenjena vrednost hipoteke ne premasuje 120 % trazene kreditne sume", z.getOdgovor());
		assertFalse(z.isStatus());
	}
	
	@Test
	public void testHipotekaPrihvacena() {
		this.zahtev.setIznos(30000);
		List<Nekretnina> obradjeneneNekretnine = new ArrayList<Nekretnina>();
		for (Nekretnina n: this.zahtev.getKlijent().getHipoteka().getNekretnine()) {
			Nekretnina obradjena = (Nekretnina) this.kieService.addObjectToSession(n, "hipoteka");
			obradjeneneNekretnine.add(obradjena);
		}
		this.zahtev.getKlijent().getHipoteka().setNekretnine(obradjeneneNekretnine);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "hipoteka");
		assertNull(z.getOdgovor());
	}
	
}
