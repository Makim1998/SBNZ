import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HOME_PATH, LOGIN_PATH } from './constants/routes';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: LOGIN_PATH,
    component: LoginComponent
  },
  {
    path: HOME_PATH,
    loadChildren: () => import('./home/home.module').then(m => m.HomeModule)
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: LOGIN_PATH
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
