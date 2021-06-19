import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTable } from '@angular/material/table';
import { Transaction } from 'src/app/models/transaction';
import { TransactionService } from 'src/app/services/transaction.service';
import { TransactionDialogComponent } from '../transaction-dialog/transaction-dialog.component';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  constructor(
    public dialog: MatDialog,
    public transactionService: TransactionService 
  ) { }

  transactions: Transaction[] = [];
  displayedColumns: string[] = ['iznos', 'tip', 'datum'];
  @ViewChild(MatTable) table: MatTable<any>;


  ngOnInit(): void {
    this.getTransactions();
  }

  getTransactions(): void {
    this.transactionService.getAllForUser().subscribe(
      (transakcije: Transaction[]) => {
        this.transactions = transakcije;
        this.table.renderRows();
      });
  }

  newTransaction(): void{
    const options: MatDialogConfig = {...{
      panelClass: 'no-padding'}, ...{width: '400px', height: '400px'}};
    this.dialog.open(TransactionDialogComponent, options).afterClosed().subscribe((result => {
        console.log("zatvoren dijalog")
        this.getTransactions();
      }));
  }

}
