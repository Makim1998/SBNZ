import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TRANSACTIONS_PATH, REQUEST_PATH, REQUEST_SEQUEL_PATH, OFFER_PATH, CREDITS_PATH } from '../constants/routes';
import { CreditsComponent } from './credits/credits.component';
import { HomeComponent } from './home.component';
import { OfferComponent } from './offer/offer.component';
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
      },
      {
        path: `${OFFER_PATH}/:id/:dan/:kamata/:rata`,
        component: OfferComponent
      },
      {
        path: CREDITS_PATH,
        component: CreditsComponent
      }
    ]
},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
