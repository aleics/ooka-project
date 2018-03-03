import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ProductComponent } from './components';
import { GeneralModule } from '../general/general.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    ProductComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    GeneralModule
  ],
  exports: [
    ProductComponent
  ],
  providers: [
  ],
  bootstrap: [
    ProductComponent
  ]
})
export class ProductModule { }
