package com.example.mapper;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ZahtevDTO;
import com.example.model.TipGarancije;
import com.example.model.TipKredita;
import com.example.model.ZahtevKredit;

@Component
public class ZahtevMapper {
	
	@Transactional(readOnly = true)
	public ZahtevKredit map(ZahtevDTO zahtevDTO) {
		ZahtevKredit zahtev = new ZahtevKredit();
		zahtev.setIznos(zahtevDTO.getIznos());
		zahtev.setPeriod(zahtevDTO.getPeriod_isplate());
		if(zahtevDTO.getTip().equals("potrosacki")) {
			zahtev.setTipKredita(TipKredita.POTROSACKI);
		}
		else if (zahtevDTO.getTip().equals("investicioni")){
			zahtev.setTipKredita(TipKredita.INVESTICIONI);
		}
		else {
			zahtev.setTipKredita(TipKredita.STAMBENI);
		}
		if (zahtevDTO.getTip().equals("hipoteka")) {
			zahtev.setTipGarancije(TipGarancije.HIPOTEKA);
		}
		else {
			zahtev.setTipGarancije(TipGarancije.ZIRANT);
		}
		zahtev.setOdgovor(zahtevDTO.getOdgovor());
		return zahtev;
	}
	
	@Transactional
	public ZahtevDTO map(ZahtevKredit zahtev) {
		ZahtevDTO zahtevDTO = new ZahtevDTO(zahtev);
		return zahtevDTO;
	}
}
