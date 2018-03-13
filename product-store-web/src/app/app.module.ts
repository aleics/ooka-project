import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { StoreModule } from './store/store.module';
import { GeneralModule } from './general/general.module';
import { ProductModule } from './product/product.module';

import {
  MatSidenavModule
} from '@angular/material';

import { AppComponent } from './app.component';
import { appRoutes } from './app.routes';
import { InitComponent } from './init.component';

@NgModule({
  declarations: [
    AppComponent,
    InitComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    StoreModule,
    ProductModule,
    GeneralModule,
    ReactiveFormsModule,
    FormsModule,

    // routes
    RouterModule.forRoot(
      appRoutes
      // { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
