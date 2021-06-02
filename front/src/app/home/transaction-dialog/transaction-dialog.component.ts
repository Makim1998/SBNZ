import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-transaction-dialog',
  templateUrl: './transaction-dialog.component.html',
  styleUrls: ['./transaction-dialog.component.css']
})
export class TransactionDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<TransactionDialogComponent>
  ) { }
  
  tipovi: String[] = ["ISPLATA", "UPLATA"];
  transactionPending = false;
  transactionForm: FormGroup = new FormGroup({
    iznos: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp(/^[0-9]\d*$/))]),
    tip: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))])
  });

  transaction(): void {

  }
  
  ngOnInit(): void {
  }

}
