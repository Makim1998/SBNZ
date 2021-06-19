import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CREDITS_PATH, HOME_PATH } from 'src/app/constants/routes';
import { SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS, SNACKBAR_SUCCESS_OPTIONS } from 'src/app/constants/snackbar';
import { KreditService } from 'src/app/services/kredit.service';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    public router: Router,
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
      this.kamata = this.roundNumber(+params.get('kamata'));
      this.rata = this.roundNumber(+params.get('rata'));
      this.id = +params.get('id');
    });
  }

  roundNumber(n: number): number{
    return Math.round((n + Number.EPSILON) *100) /100;
  }

  accept(): void{
    this.snackBar.open("Ponuda potvrdjena", SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
    this.offerPending = true;
    console.log(this.id);
    this.kreditService.acceptOffer(this.id, this.dan).subscribe(
      (response: boolean) => {
        this.offerPending = false;
        if (response){
          this.snackBar.open('Kredit je ugovoren',
          SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
          this.router.navigate([`${HOME_PATH}/${CREDITS_PATH}`]);
        }
        else{
          this.snackBar.open("Nesto je poslo po zlu :(", SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
        }
      }
    );
  }

  decline(): void{
    this.offerPending = true;
    console.log(this.id);
    this.kreditService.declineOffer(this.id).subscribe(
      (response: boolean) => {
        this.offerPending = false;
        if (response){
          this.snackBar.open('Ponuda odbijena.',
          SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
          this.router.navigate([`${HOME_PATH}/${CREDITS_PATH}`]);
        }
        else{
          this.snackBar.open("Nesto je poslo po zlu :(", SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
        }
      }
    );
  }
  

}
