import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ChatEvent, ChatEventType, ChatMessage } from '../models';
import { EndpointService } from '../../general/services';
import { Observable } from 'rxjs/Observable';
import * as _ from 'lodash';

@Injectable()
export class MessagesService {
  constructor(
    private endpointService: EndpointService,
    private http: HttpClient
  ) {}

  readMessages(channelId: string): Observable<ChatMessage[]> {
    const url = this.endpointService.getMessagesEndpoint(channelId);

    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    return this.http.get<ChatMessage[]>(url, { headers });
  }
}
