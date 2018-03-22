import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ChannelsService, MessagesService } from '../../services';
import { StorageService } from '../../../general/services';
import { UserRole, User } from '../../../general/models';
import { ChatChannel, ChatMessage } from '../../models';

import { Observable } from 'rxjs/Observable';


@Component({
  selector: 'ps-chat-container',
  templateUrl: './chat-container.component.html',
  styleUrls: ['./chat-container.component.styl']
})
export class ChatContainerComponent implements OnInit {
  isLoading = true;
  messages: ChatMessage[];
  user: User;

  @ViewChild('chatContainer') private chatContainer: ElementRef;

  constructor(
    private storageService: StorageService,
    private channelsService: ChannelsService,
    private messagesService: MessagesService
  ) {}

  // if user role is ADMIN: load all chat channels
  // if user role is USER: make a post to create or read a channel
  ngOnInit() {
    this.user = this.storageService.getUserData();
    this.fetchChannels(this.user.id, this.user.userRole)
      .subscribe((messages) => {
        this.messages = messages;
        this.isLoading = false;
      });
  }

  sendMessage(text: string) {
    const channelId = this.user.id;
    const message: ChatMessage = {
      message: text,
      sender: this.user.email,
      channelId: this.user.id
    };

    this.messagesService.sendMessage(message, channelId)
      .subscribe((chatMessage) => {
        this.messages.push(chatMessage);
      });
  }

  private fetchChannels(channelId: string, userRole: UserRole) {
    return this.channelsService.createChannel(channelId)
      .flatMap((channel: ChatChannel) =>
        this.messagesService.readMessages(channel.id)
      );
  }
}
