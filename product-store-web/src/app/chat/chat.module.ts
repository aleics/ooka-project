import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material';
import { GeneralModule } from '../general/general.module';
import { MessagesService, ChannelsService, ChatGuardService } from './services';
import { ChatContainerComponent, ChatInputComponent, ChatBodyComponent, ChatMessageComponent } from './components';

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
    RouterModule,
    GeneralModule
  ],
  exports: [
    ChatContainerComponent,
    ChatInputComponent,
    ChatBodyComponent,
    ChatMessageComponent
  ],
  providers: [
    MessagesService,
    ChannelsService,
    ChatGuardService
  ],
  bootstrap: [
    ChatContainerComponent,
    ChatInputComponent,
    ChatBodyComponent,
    ChatMessageComponent
  ]
})
export class ChatModule { }
