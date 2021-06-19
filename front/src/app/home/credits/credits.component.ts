import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { Credit } from 'src/app/models/credit';
import { KreditService } from 'src/app/services/kredit.service';

@Component({
  selector: 'app-credits',
  templateUrl: './credits.component.html',
  styleUrls: ['./credits.component.css']
})
export class CreditsComponent implements OnInit {

  constructor(
    public kreditService: KreditService
  ) { }

  credits: Credit[] = [];
  displayedColumns: string[] = ['datum', 'iznos', 'period', 'rata', 'kamata'];
  @ViewChild(MatTable) table: MatTable<any>;

  ngOnInit(): void {
    this.getCredits();
  }

  roundNumber(n: number): number{
    return Math.round((n + Number.EPSILON) *100) /100;
  }

  getCredits(): void {
    this.kreditService.getAllForUser().subscribe(
      (krediti: Credit[]) => {
      this.credits = krediti;
      this.credits.map(c => {c.kamata = this.roundNumber(c.kamata); c.mesecna_rata = this.roundNumber(c.mesecna_rata);})
      this.table.renderRows();
    });
  }

}
