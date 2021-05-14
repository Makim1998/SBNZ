import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { RequestComponent } from './request/request.component';
import { HomeComponent } from './home.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '../layout/layout.module';



@NgModule({
  declarations: [
    RequestComponent,
    HomeComponent
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
