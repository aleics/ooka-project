import { Component, Input, EventEmitter, Output } from '@angular/core';

import * as _ from 'lodash';

@Component({
  selector: 'ps-chat-input',
  templateUrl: './chat-input.component.html',
  styleUrls: ['./chat-input.component.styl']
})
export class ChatInputComponent {
  @Input() public disabled = false;
  @Output() public send: EventEmitter<string> = new EventEmitter();

  value: string;

  sendMessage() {
    if (!_.isEmpty(this.value)) {
      this.send.emit(this.value);
      this.value = null; // reset value to empty
    }
  }
}
