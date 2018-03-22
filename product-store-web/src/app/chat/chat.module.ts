import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ChatContainerComponent } from './components';

@NgModule({
  declarations: [
    ChatContainerComponent
  ],
  imports: [
    BrowserModule,
    RouterModule
  ],
  exports: [
    ChatContainerComponent
  ],
  providers: [],
  bootstrap: [
    ChatContainerComponent
  ]
})
export class ChatModule { }
