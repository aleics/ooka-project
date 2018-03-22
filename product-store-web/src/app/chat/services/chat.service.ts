import { Injectable, EventEmitter } from '@angular/core';
import { ChatEvent, ChatEventType, ChatMessage } from '../models';
import * as _ from 'lodash';
import { EndpointService } from '../../general/services';

@Injectable()
export class ChatService {
  constructor(
    private endpointService: EndpointService
  ) {}

  readMessages(channelId: string) {

  }

  createChannel(userId: string) {

  }
}
