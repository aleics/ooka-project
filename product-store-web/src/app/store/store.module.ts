import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {
  HomeComponent,
  MenuIconComponent,
  SidebarComponent,
  TopbarComponent,
  ProductsListComponent,
  ProductListItemComponent
} from './components';
import { GeneralModule } from '../general/general.module';


@NgModule({
  declarations: [
    HomeComponent,
    MenuIconComponent,
    ProductsListComponent,
    ProductListItemComponent,
    SidebarComponent,
    TopbarComponent
  ],
  imports: [
    GeneralModule,
    BrowserModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: [
    HomeComponent,
    MenuIconComponent,
    ProductsListComponent,
    ProductListItemComponent,
    SidebarComponent,
    TopbarComponent
  ],
  providers: [],
  bootstrap: [
    HomeComponent,
    MenuIconComponent,
    ProductsListComponent,
    ProductListItemComponent,
    SidebarComponent,
    TopbarComponent
  ]
})
export class StoreModule { }
