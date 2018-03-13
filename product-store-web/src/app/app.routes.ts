import { InitComponent } from './init.component';
import { HomeComponent } from './store/components';
import { ProductComponent } from './product/components';
import { PageNotFoundComponent } from './general/components';
import { Routes } from '@angular/router';
import { LoginComponent } from './login/components/login/login.component';

export const appRoutes: Routes = [
  {
    path: '',
    component: InitComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent
      },
      { path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      {
        path: 'product/:productId',
        component: ProductComponent
      }
    ]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  { path: '**', component: PageNotFoundComponent }
];
