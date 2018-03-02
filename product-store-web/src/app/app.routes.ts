import { HomeComponent } from './store/components';
import { ProductComponent } from './product/components';
import { PageNotFoundComponent } from './general/components';
import { Routes } from '@angular/router';

export const appRoutes: Routes = [
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
  },
  { path: '**', component: PageNotFoundComponent }
];
