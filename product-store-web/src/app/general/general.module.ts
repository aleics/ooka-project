import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {
  MatProgressSpinnerModule
} from '@angular/material';
import {
  PageNotFoundComponent, SpinnerComponent
} from './components';
import {
  ProductsService,
  EndpointService
} from './services';


@NgModule({
  declarations: [
    PageNotFoundComponent,
    SpinnerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatProgressSpinnerModule
  ],
  exports: [
    PageNotFoundComponent,
    SpinnerComponent
  ],
  providers: [
    ProductsService,
    EndpointService
  ],
  bootstrap: [
    PageNotFoundComponent,
    SpinnerComponent
  ]
})
export class GeneralModule { }
