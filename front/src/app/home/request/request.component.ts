import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SNACKBAR_CLOSE, SNACKBAR_ERROR, SNACKBAR_ERROR_OPTIONS, SNACKBAR_SUCCESS_OPTIONS } from '../../constants/snackbar';
import { RequestService } from 'src/app/services/request.service';
import { RequestCredit } from '../../models/request'
import { REQUEST_SEQUEL_PATH } from 'src/app/constants/routes';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})

export class RequestComponent implements OnInit {
  garancije: String[] = ['zirant', 'hipoteka'];
  tipovi: String[] = ['potrosacki','stambeni', 'investicioni'];

  constructor(
    public requestService: RequestService,
    public snackBar: MatSnackBar,
    public router: Router,
    private route: ActivatedRoute
  ) { }

  requestPending = false;
  requestForm: FormGroup = new FormGroup({
    iznos: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp(/^[0-9]\d*$/))]),
    period: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp(/^[0-9]\d*$/))]),
    tip: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    garancija: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
  });

  request(): void{
    if (this.requestForm.invalid){
      return;
    }
    this.requestPending = true;
    this.requestService.request(this.requestForm.value).subscribe(
      (request: RequestCredit) => {
        this.requestPending = false;
        if(request.status){
          this.snackBar.open(request.odgovor, SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
          console.log(request.garancija);
          console.log(request.id);
          this.router.navigate(['home/request-sequel', request.garancija === 'HIPOTEKA' ? 'hipoteka': 'zirant', request.id]);
        }
        else{
          this.snackBar.open(request.odgovor, SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
        }
        console.log(request);
      }
    );
  } 

  ngOnInit(): void {
  }

}
