import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HOME_PATH, REQUEST_PATH } from '../constants/routes';
import { HomeComponent } from './home.component';
import { RequestComponent } from './request/request.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: REQUEST_PATH,
        component: RequestComponent
      }
    ]
},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
