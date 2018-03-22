import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ChatContainerComponent, ChatInputComponent, ChatBodyComponent, ChatMessageComponent } from './components';
import { MatInputModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ChatContainerComponent,
    ChatInputComponent,
    ChatBodyComponent,
    ChatMessageComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    BrowserModule,
    RouterModule
  ],
  exports: [
    ChatContainerComponent,
    ChatInputComponent,
    ChatBodyComponent,
    ChatMessageComponent
  ],
  providers: [],
  bootstrap: [
    ChatContainerComponent,
    ChatInputComponent,
    ChatBodyComponent,
    ChatMessageComponent
  ]
})
export class ChatModule { }