package com.example.backend;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Klijent;
import com.example.model.Kredit;
import com.example.model.TipGarancije;
import com.example.model.TipKredita;
import com.example.model.ZahtevKredit;
import com.example.service.KieService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NagradniTest {
	
	@Autowired
	private KieService kieService;
	
	private ZahtevKredit zahtev;
	
	@Before
	public void init() {
		ZahtevKredit z = new ZahtevKredit();
		z.setIznos(10000);
		z.setPeriod(120);
		z.setTipGarancije(TipGarancije.HIPOTEKA);
		z.setTipKredita(TipKredita.STAMBENI);
		z.setOdgovor("ok");
		z.setStatus(true);
		
		Klijent k = new Klijent();
		k.setGodine(50);
		k.setMesecna_zarada(25000);
		k.setPridruzen(new Date());
		k.setNagradni_poeni(0);
		z.setKlijent(k);
		List<Kredit> krediti = new ArrayList<Kredit>();
		k.setKrediti(krediti);
		this.zahtev = z;
	}
	
	@Test
	public void test1() {
		this.zahtev.setTipGarancije(TipGarancije.ZIRANT);
		this.zahtev.setIznos(13000);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "osnovnaKamata");
		assertEquals(2, z.getKlijent().getNagradni_poeni());
	}
	
	@Test
	public void test2() {
		this.zahtev.setTipKredita(TipKredita.STAMBENI);
		this.zahtev.setIznos(25000);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "osnovnaKamata");
		assertEquals(2, z.getKlijent().getNagradni_poeni());
	}
	
	@Test
	public void test4() {
		this.zahtev.setTipKredita(TipKredita.POTROSACKI);
		this.zahtev.setIznos(30000);
		List<Kredit> krediti = new ArrayList<Kredit>();
		this.zahtev.getKlijent().setKrediti(krediti);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "osnovnaKamata");
		assertEquals(4, z.getKlijent().getNagradni_poeni());
	}
	
	@Test
	public void test6() {
		this.zahtev.setTipKredita(TipKredita.STAMBENI);
		this.zahtev.setIznos(60000);
		List<Kredit> krediti = new ArrayList<Kredit>();
		this.zahtev.getKlijent().setKrediti(krediti);
		ZahtevKredit z = (ZahtevKredit) this.kieService.addObjectToSession(this.zahtev, "osnovnaKamata");
		assertEquals(6, z.getKlijent().getNagradni_poeni());
	}

}
