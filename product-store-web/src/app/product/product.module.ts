import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { ProductComponent } from './components';
import { GeneralModule } from '../general/general.module';

@NgModule({
  declarations: [
    ProductComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
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
