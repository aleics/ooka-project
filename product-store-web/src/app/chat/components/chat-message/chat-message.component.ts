import { Component, Input } from '@angular/core';
import { ChatMessage } from '../../models/chat-message.interface';

@Component({
  selector: 'ps-chat-message',
  templateUrl: './chat-message.component.html',
  styleUrls: ['./chat-message.component.styl']
})
export class ChatMessageComponent {
  @Input() public message: ChatMessage;
  @Input() public uuid: string;

  date = new Date();
}
