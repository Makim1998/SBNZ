package com.example.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
public class UsloviZahteva {
	
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
		z.setOdgovor("ok");
		z.setStatus(true);
		
		Kredit kredit = new Kredit();
		z.setKredit(kredit);
		
		Klijent k = new Klijent();
		k.setGodine(50);
		k.setMesecna_zarada(25000);
		
		z.setKlijent(k);
		k.setHipoteka(h);
		this.zahtev = z;
	}
	
	@Test
	public void testPrihvacen() {
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "zahtev");
		assertEquals("ok", z.getOdgovor());
	}
	
	@Test
	public void testOdbijen1() {
		this.zahtev.setIznos(500000);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "zahtev");
		assertEquals("Iznos ne sme biti veci od 300000 evra.", z.getOdgovor());
		assertFalse(z.isStatus());
	}
	
	@Test
	public void testOdbijen3() {
		this.zahtev.setPeriod(301);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "zahtev");
		assertEquals("Period isplate ne sme biti duzi od 25 godina.", z.getOdgovor());
		assertFalse(z.isStatus());
	}
	
	@Test
	public void testOdbijen4() {
		this.zahtev.setPeriod(11);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "zahtev");
		assertEquals("Period isplate ne sme biti kraci od godinu dana.", z.getOdgovor());
		assertFalse(z.isStatus());
	}
	
	@Test
	public void testOdbijen5() {
		this.zahtev.setTipKredita(TipKredita.STAMBENI);
		this.zahtev.setIznos(13000);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "zahtev");
		assertEquals("Za stambeni kredit suma mora biti preko 20000 evra.", z.getOdgovor());
		assertFalse(z.isStatus());
	}
	
	@Test
	public void testOdbijen6() {
		this.zahtev.setTipKredita(TipKredita.INVESTICIONI);
		this.zahtev.setIznos(35000);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "zahtev");
		assertEquals("Za investicioni kredit suma mora biti preko 40000 evra.", z.getOdgovor());
		assertFalse(z.isStatus());
	}
	
	@Test
	public void testOdbijen7() {
		this.zahtev.setTipKredita(TipKredita.POTROSACKI);
		this.zahtev.setIznos(40000);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "zahtev");
		assertEquals("Za potrosacki kredit suma mora biti manja od 30000 evra.", z.getOdgovor());
		assertFalse(z.isStatus());
	}

}
