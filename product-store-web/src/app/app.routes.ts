import { HomeComponent } from './store/components';
import { PageNotFoundComponent } from './general/components'
import { Routes } from '@angular/router';

export const appRoutes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  { path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  { path: '**', component: PageNotFoundComponent }
];