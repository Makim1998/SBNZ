import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTable } from '@angular/material/table';
import { Nekretnina } from 'src/app/models/nekretnina';
import { NekretninaDialogComponent } from '../nekretnina-dialog/nekretnina-dialog.component';

@Component({
  selector: 'app-request-sequel',
  templateUrl: './request-sequel.component.html',
  styleUrls: ['./request-sequel.component.css']
})
export class RequestSequelComponent implements OnInit {

  constructor(
    public dialog: MatDialog
  ) { }

  nekretnine: Nekretnina[] = [];
  displayedColumns: string[] = ['zona', 'tip', 'kvadratura', 'brisanje'];
  @ViewChild(MatTable) table: MatTable<any>;
  
  ngOnInit(): void {
  }

  nekretnina(): void {
    const options: MatDialogConfig = {...{
      panelClass: 'no-padding', disableClose: true}, ...{width: '500px', height: '500px'}};
    this.dialog.open(NekretninaDialogComponent, options).afterClosed().subscribe((result => {
      if (result){
        console.log(result);
        this.nekretnine.push(result);
        console.log(this.nekretnine);
        this.table.renderRows();
      }
    }));
  }

  delete(n: Nekretnina): void{
    const index: number = this.nekretnine.indexOf(n);
    if (index !== -1) {
        this.nekretnine.splice(index, 1);
    }        
    this.table.renderRows();
  }

}
