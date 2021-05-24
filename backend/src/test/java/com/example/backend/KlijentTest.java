package com.example.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.example.model.Kredit;
import com.example.model.Nekretnina;
import com.example.model.TipGarancije;
import com.example.model.TipKredita;
import com.example.model.TipNekretnine;
import com.example.model.ZahtevKredit;
import com.example.model.ZonaNekretnine;
import com.example.service.KieService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KlijentTest {
	

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
		z.setStatus(true);
		
		Klijent k = new Klijent();
		k.setGodine(50);
		k.setMesecna_zarada(2500);
		
		z.setKlijent(k);
		k.setHipoteka(h);
		this.zahtev = z;
	}
	
	@Test
	public void testKlijentPrihvacen() {
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertTrue(z.isStatus());
	}
	@Test
	public void testKlijentOdbijen1() {
		this.zahtev.getKlijent().setGodine(66);
		this.zahtev.setPeriod(121);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals("Za klijente starije od 65 godina period isplate mora biti kraci od 10 godina.", z.getOdgovor());
		assertFalse(z.isStatus());
	}
	
	@Test
	public void testKlijentOdbijen2() {
		this.zahtev.getKlijent().setMesecna_zarada(800);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals("Osnovna mesecna rata premasuje 30% vasih primanja.", z.getOdgovor());
		assertFalse(z.isStatus());
	}
	
	@Test
	public void testKlijentOdbijen3() {
		List<Kredit> krediti = new ArrayList<Kredit>();
	
		Kredit k1 = new Kredit();
		Kredit k2 = new Kredit();
		this.zahtev.setTipKredita(TipKredita.INVESTICIONI);
		k1.setZahtev(this.zahtev);
		k2.setZahtev(this.zahtev);
		
		krediti.add(k2);
		krediti.add(k1);
		
		this.zahtev.getKlijent().setKrediti(krediti);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals("Vec ste podigli dva ili vise investicionih i potrosackih kredita.", z.getOdgovor());
		assertFalse(z.isStatus());
	}

}
