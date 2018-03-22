import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../services';

@Component({
  selector: 'ps-chat-container',
  templateUrl: './chat-container.component.html',
  styleUrls: ['./chat-container.component.styl']
})
export class ChatContainerComponent implements OnInit {
  constructor(
    private chatService: ChatService
  ) {}

  ngOnInit() {

  }
}
