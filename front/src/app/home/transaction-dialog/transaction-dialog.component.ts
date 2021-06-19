import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS, SNACKBAR_SUCCESS_OPTIONS } from 'src/app/constants/snackbar';
import { Transaction } from 'src/app/models/transaction';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transaction-dialog',
  templateUrl: './transaction-dialog.component.html',
  styleUrls: ['./transaction-dialog.component.css']
})
export class TransactionDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<TransactionDialogComponent>,
    public transactionService: TransactionService,
    public snackBar: MatSnackBar
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
    this.transactionService.transaction(this.transactionForm.value).subscribe(
      (transakcija: Transaction) => {
        if(transakcija != null){
          this.snackBar.open("Transakcija je uspela!", SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS );
          this.dialogRef.close();
        }
        else{
          this.snackBar.open("Transakcija nije uspela nemate dovoljno novca!", SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
        }
      });

  }
  
  ngOnInit(): void {
  }

}
