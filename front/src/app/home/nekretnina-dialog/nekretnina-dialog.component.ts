import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Option } from 'src/app/models/option';

@Component({
  selector: 'app-nekretnina-dialog',
  templateUrl: './nekretnina-dialog.component.html',
  styleUrls: ['./nekretnina-dialog.component.css']
})
export class NekretninaDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<NekretninaDialogComponent>
  ) { }
  
  tipovi: Option[] = [{view: "stan", value: 0}, {view: "kuca", value: 1}];
  zone: Option[] = [{view: "I", value: 0}, {view: "II", value: 1}, {view:"III", value:2}];

  nekretninaForm: FormGroup = new FormGroup({
    kvadratura: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp(/^[0-9]\d*$/))]),
    tip: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    zona: new FormControl('', [Validators.required,
      Validators.pattern(new RegExp('\\S'))])
  });

  ngOnInit(): void {
  }

  nekretnina(): void {
      this.dialogRef.close(this.nekretninaForm.value);
  }

}
