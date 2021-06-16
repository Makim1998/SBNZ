import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TRANSACTIONS_PATH, REQUEST_PATH, REQUEST_SEQUEL_PATH } from '../constants/routes';
import { HomeComponent } from './home.component';
import { RequestSequelComponent } from './request-sequel/request-sequel.component';
import { RequestComponent } from './request/request.component';
import { TransactionsComponent } from './transactions/transactions.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: REQUEST_PATH,
        component: RequestComponent
      },
      {
        path: `${REQUEST_SEQUEL_PATH}/:type/:request`,
        component: RequestSequelComponent
      },
      {
        path: TRANSACTIONS_PATH,
        component: TransactionsComponent
      }
    ]
},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
