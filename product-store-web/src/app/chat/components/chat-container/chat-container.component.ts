import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ChannelsService, MessagesService } from '../../services';
import { StorageService } from '../../../general/services';
import { UserRole, User } from '../../../general/models';
import { ChatChannel, ChatMessage } from '../../models';

import 'rxjs/add/observable/of';


@Component({
  selector: 'ps-chat-container',
  templateUrl: './chat-container.component.html',
  styleUrls: ['./chat-container.component.styl']
})
export class ChatContainerComponent implements OnInit {
  isLoading = true;
  messages: ChatMessage[] = [];
  user: User;
  channels: ChatChannel[] = [];
  currentChannelId: string;

  get isAdmin(): boolean {
    return this.user.userRole === 'ADMIN';
  }

  get showChannelSelector(): boolean {
    return this.isAdmin && this.channels.length > 0;
  }

  @ViewChild('chatContainer') private chatContainer: ElementRef;

  constructor(
    private storageService: StorageService,
    private channelsService: ChannelsService,
    private messagesService: MessagesService
  ) {}

  // if user role is ADMIN: load all chat channels
  // if user role is USER: make a post to create or read the user channel
  ngOnInit() {
    this.user = this.storageService.getUserData();

    if (this.isAdmin) {
      this.getMessagesForAdmin()
      .subscribe((messages) => this.readMessages(messages));
    } else {
      const inputChannel: ChatChannel = {
        id: this.user.id,
        name: this.user.email
      };
      this.getMessagesForUser(inputChannel)
        .subscribe((messages) => this.readMessages(messages));
    }
  }

  getMessagesForAdmin() {
    return this.getCurrentChannels()
    .flatMap((channels) => {
      if (channels && channels.length > 0) {
        const channelId = channels[0].id;
        this.channels = channels;
        this.currentChannelId = channelId;
        return this.messagesService.readMessages(channelId);
      } else {
        return Observable.of([]);
      }
    });
  }

  getMessagesForUser(inputChannel: ChatChannel) {
    return this.fetchMessages(inputChannel)
      .flatMap(({channel, messages}) => {
        this.currentChannelId = channel.id;
        return Observable.of(messages);
      });
  }

  sendMessage(text: string) {
    const message: ChatMessage = {
      message: text,
      sender: this.user.email,
      channelId: this.currentChannelId
    };

    this.messagesService.sendMessage(message, this.currentChannelId)
    .subscribe((chatMessage) => {
      this.messages.push(chatMessage);
    });
  }

  private readMessages(messages: ChatMessage[]) {
    this.messages = messages;
    this.isLoading = false;
  }

  private getCurrentChannels(): Observable<ChatChannel[]> {
    return this.channelsService.loadChannels();
  }

  private fetchMessages(inputChannel: ChatChannel) {
    return this.channelsService.createChannel(inputChannel)
      .flatMap((channel: ChatChannel) =>
        this.messagesService.readMessages(channel.id)
          .map((messages) => {
            return { channel, messages };
          })
      );
  }
}
