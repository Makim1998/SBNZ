import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Transaction } from 'src/app/models/transaction';
import { TransactionDialogComponent } from '../transaction-dialog/transaction-dialog.component';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  constructor(
    public dialog: MatDialog
  ) { }

  transactions: Transaction[] = [];
  displayedColumns: string[] = ['iznos', 'tip', 'datum'];

  ngOnInit(): void {
  }

  newTransaction(): void{
    const options: MatDialogConfig = {...{
      panelClass: 'no-padding'}, ...{width: '400px', height: '400px'}};
    this.dialog.open(TransactionDialogComponent, options);
  }

}
