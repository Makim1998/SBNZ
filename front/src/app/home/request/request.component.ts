import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {
  garancije: String[] = ['zirant', 'hipoteka'];
  tipovi: String[] = ['potrosacki','stambeni', 'investicioni'];

  constructor() { }

  requestPending = false;
  requestForm: FormGroup = new FormGroup({
    iznos: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp(/^[0-9]\d*$/))]),
    period: new FormControl('', [Validators.required,
      Validators.pattern(new RegExp('\\S'))]),
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
  } 

  ngOnInit(): void {
  }

}
