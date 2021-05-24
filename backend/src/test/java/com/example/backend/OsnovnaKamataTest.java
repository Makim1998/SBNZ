package com.example.backend;

import static org.junit.Assert.assertEquals;

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
public class OsnovnaKamataTest {

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
	public void testKamata4a() {
		this.zahtev.setIznos(8000);
		this.zahtev.setPeriod(3 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(4, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata4b() {
		this.zahtev.setIznos(20000);
		this.zahtev.setPeriod(4 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(4, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata4c() {
		this.zahtev.setIznos(50000);
		this.zahtev.setPeriod(6 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(4, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata4d() {
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(4, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata4e() {
		this.zahtev.setIznos(100001);
		this.zahtev.setPeriod(14 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(4, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata6a() {
		this.zahtev.setIznos(8000);
		this.zahtev.setPeriod(4 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(6, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata6b() {
		this.zahtev.setIznos(20000);
		this.zahtev.setPeriod(6 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(6, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata6c() {
		this.zahtev.setIznos(50000);
		this.zahtev.setPeriod(9 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(6, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata6d() {
		this.zahtev.setPeriod(12 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(6, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata6e() {
		this.zahtev.setIznos(100001);
		this.zahtev.setPeriod(16 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(6, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata8a() {
		this.zahtev.setIznos(8000);
		this.zahtev.setPeriod(6 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(8, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata8b() {
		this.zahtev.setIznos(20000);
		this.zahtev.setPeriod(9 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(8, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata8c() {
		this.zahtev.setIznos(50000);
		this.zahtev.setPeriod(12 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(8, z.getKredit().getKamata());
	}
	
	@Test
	public void testKamata8d() {
		this.zahtev.setPeriod(16 * 12);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev);
		assertEquals(8, z.getKredit().getKamata());
	}

}
