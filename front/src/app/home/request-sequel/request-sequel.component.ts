import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router'
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTable } from '@angular/material/table';
import { Nekretnina } from 'src/app/models/nekretnina';
import { NekretninaDialogComponent } from '../nekretnina-dialog/nekretnina-dialog.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { RequestSequelService } from 'src/app/services/request-sequel.service';
import { Zirant } from 'src/app/models/zirant';
import { RequestCredit } from 'src/app/models/request';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS, SNACKBAR_SUCCESS_OPTIONS } from 'src/app/constants/snackbar';
import { Hipoteka } from 'src/app/models/hipoteka';

@Component({
  selector: 'app-request-sequel',
  templateUrl: './request-sequel.component.html',
  styleUrls: ['./request-sequel.component.css']
})
export class RequestSequelComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    public dialog: MatDialog,
    public userService: UserService,
    public requestService: RequestSequelService,
    public snackBar: MatSnackBar
  ) { }

  
  hipoteka: boolean = true;
  zahtev: number = 0;
  nekretnine: Nekretnina[] = [];
  displayedColumns: string[] = ['zona', 'tip', 'kvadratura', 'brisanje'];
  @ViewChild(MatTable) table: MatTable<any>;
  pending: boolean = false;
  zirantForm: FormGroup = new FormGroup({
    zirant: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp(/^[0-9]\d*$/))])
  });
  ziranti: User[] = [];
  
  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.hipoteka = params.get('type') === 'hipoteka';
      this.zahtev = +params.get('request');
    })
    if (!this.hipoteka){
      this.userService.getAllClients().subscribe(
        (ziranti: User[]) => {
          this.ziranti = ziranti;
        });
    } 
    
  }

  nekretnina(): void {
    const options: MatDialogConfig = {...{
      panelClass: 'no-padding', disableClose: true}, ...{width: '500px', height: '500px'}};
    this.dialog.open(NekretninaDialogComponent, options).afterClosed().subscribe((result => {
      if (result){
        console.log(result);
        result.tipView = result.tip === 0 ? "stan": "kuca";
        result.zonaView = result.zona === 0 ? "I" : result.zona === 1 ? "II": "III";
        this.nekretnine.push(result);
        console.log(this.nekretnine);
        this.table.renderRows();
      }
    }));
  }

  hipotekica(): void {
    this.pending = true;
    console.log(this.nekretnine);
    const h: Hipoteka = {nekretnine: this.nekretnine}
    this.requestService.hipoteka(h, this.zahtev).subscribe(
      (request: RequestCredit) => {
        this.pending = false;
        if(request.status){
          this.snackBar.open(request.odgovor, SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
          console.log(request.id);
          //this.router.navigate([REQUEST_SEQUEL_PATH, this.requestForm.controls['garancija'].value, request.id]);
        }
        else{
          this.snackBar.open(request.odgovor, SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
        }
        console.log(request);
      }
    );
  }
    
  zirantic(): void {
    this.pending = true;
    const z: Zirant = {
      zirant : +this.zirantForm.controls["zirant"].value,
      zahtev : this.zahtev
    }
    console.log(z);
    this.requestService.zirant(z).subscribe(
      (request: RequestCredit) => {
        this.pending = false;
        if(request.status){
          this.snackBar.open(request.odgovor, SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
          console.log(request.id);
          //this.router.navigate([REQUEST_SEQUEL_PATH, this.requestForm.controls['garancija'].value, request.id]);
        }
        else{
          this.snackBar.open(request.odgovor, SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
        }
      }
    );
  }
  

  delete(n: Nekretnina): void{
    const index: number = this.nekretnine.indexOf(n);
    if (index !== -1) {
        this.nekretnine.splice(index, 1);
    }        
    this.table.renderRows();
  }

}
