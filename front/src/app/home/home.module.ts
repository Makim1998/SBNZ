import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { RequestComponent } from './request/request.component';
import { HomeComponent } from './home.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '../layout/layout.module';
import { TransactionsComponent } from './transactions/transactions.component';
import { TransactionDialogComponent } from './transaction-dialog/transaction-dialog.component';
import { RequestSequelComponent } from './request-sequel/request-sequel.component';
import { NekretninaDialogComponent } from './nekretnina-dialog/nekretnina-dialog.component';
import { OfferComponent } from './offer/offer.component';
import { CreditsComponent } from './credits/credits.component';



@NgModule({
  declarations: [
    RequestComponent,
    HomeComponent,
    TransactionsComponent,
    TransactionDialogComponent,
    RequestSequelComponent,
    NekretninaDialogComponent,
    OfferComponent,
    CreditsComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    LayoutModule,
    ReactiveFormsModule,
    HomeRoutingModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class HomeModule { }
