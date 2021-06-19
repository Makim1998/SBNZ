import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS } from 'src/app/constants/snackbar';
import { KreditService } from 'src/app/services/kredit.service';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    public kreditService: KreditService,
    private snackBar: MatSnackBar
  ) { }

  dan: number = 1;
  id: number;
  dani = Array.from(Array(28).keys());
  kamata: number = 0;
  rata: number = 0;
  offerPending: boolean = false;

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.dan = +params.get('dan');
      this.kamata = +params.get('kamata');
      this.rata = +params.get('rata');
      this.id = +params.get('id');
    });
  }

  accept(): void{
    this.snackBar.open("Ponuda potvrdjena", SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
  }

  decline(): void{
    console.log(this.id);
    this.kreditService.declineOffer(this.id);
    this.snackBar.open("Ponuda odbijena", SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
  }

}
