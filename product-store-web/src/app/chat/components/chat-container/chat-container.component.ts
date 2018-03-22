import { Component, OnInit } from '@angular/core';
import { ChannelsService, MessagesService } from '../../services';
import { StorageService } from '../../../general/services';
import { UserRole } from '../../../general/models';
import { ChatChannel } from '../../models';

import { Observable } from 'rxjs/Observable';


@Component({
  selector: 'ps-chat-container',
  templateUrl: './chat-container.component.html',
  styleUrls: ['./chat-container.component.styl']
})
export class ChatContainerComponent implements OnInit {
  constructor(
    private storageService: StorageService,
    private channelsService: ChannelsService,
    private messagesService: MessagesService
  ) {}

  // if user role is ADMIN: load all chat channels
  // if user role is USER: make a post to create or read a channel
  ngOnInit() {
    const user = this.storageService.getUserData();
    this.fetchChannels(user.id, user.userRole)
      .subscribe((messages) => {
        console.log(messages);
      });
  }

  private fetchChannels(channelId: string, userRole: UserRole) {
    return this.channelsService.createChannel(channelId)
      .flatMap((channel: ChatChannel) =>
        this.messagesService.readMessages(channel.id)
      );
  }
}
